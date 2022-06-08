package com.salesianostriana.dam.ProyectoFinal.services;

import com.salesianostriana.dam.ProyectoFinal.config.StorageProperties;
import com.salesianostriana.dam.ProyectoFinal.errors.exceptions.FileNotFoundException;
import com.salesianostriana.dam.ProyectoFinal.errors.exceptions.StorageException;
import com.salesianostriana.dam.ProyectoFinal.errors.exceptions.WrongFormatException;
import com.salesianostriana.dam.ProyectoFinal.utils.MediaTypeUrlResource;
import io.github.techgnious.exception.VideoException;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService{

    private final Path rootLocation;

    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }


    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String newFilename = "";
        List<String> validMimeFormat = List.of("png", "jpg", "jpeg" , "avi", "mp4");

        try {
            // Si el fichero está vacío, excepción al canto
            if (file.isEmpty())
                throw new StorageException("El fichero subido está vacío");
            if(!validMimeFormat.contains(StringUtils.getFilenameExtension(filename))) throw new WrongFormatException("Hubo un error. El formato no es válido.");
            newFilename = filename;
            while(Files.exists(rootLocation.resolve(newFilename))) {
                // Tratamos de generar uno nuevo
                String extension = StringUtils.getFilenameExtension(newFilename);
                String name = newFilename.replace("."+extension,"");

                String suffix = Long.toString(System.currentTimeMillis());
                suffix = suffix.substring(suffix.length()-6);

                newFilename = name + "_" + suffix + "." + extension;

            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve(newFilename),
                        StandardCopyOption.REPLACE_EXISTING);
            }



        } catch (IOException ex) {
            throw new StorageException("Error en el almacenamiento del fichero: " + newFilename, ex);
        }

        return newFilename;

    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Error al leer los ficheros almacenados", e);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {

        try {
            Path file = load(filename);
            MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }


    public void deleteFile(String filename) {

        try{
            Path file = load(filename);
            MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                Files.deleteIfExists(file);
            }
        }catch (MalformedURLException e){
            throw new FileNotFoundException("No se encuentra el archivo");
        } catch (IOException e) {
            throw new StorageException("No se ha encontrado el archivo", e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public BufferedImage simpleResizeImage(BufferedImage originalImage, int targetWidth) throws Exception {
        return null;
    }

    @Override
    public byte[] resizeVideo(MultipartFile file, int width, int height, String mimeFormat) throws IOException, VideoException {
        return new byte[0];
    }

    public BufferedImage scale(String filename){

        try{

            byte[] byteImg = Files.readAllBytes(rootLocation.resolve(filename));
            BufferedImage original = ImageIO.read(new ByteArrayInputStream(byteImg));
            BufferedImage scaled = Scalr.resize(original,1024);
            return scaled;

        }catch (MalformedURLException e){

            throw new FileNotFoundException("No se ha encontrado el archivo", e);

        } catch (IOException e) {

            throw new StorageException("No se ha encontrado el archivo", e);
        }


    }

    public String convertToUri(String filename){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
    }

}
