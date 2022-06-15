import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/especialidad_response.dart';
import 'package:flutter_proyecto_final/models/medico_edit.dart';
import 'package:flutter_proyecto_final/models/medico_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository_impl.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';
import 'package:flutter_proyecto_final/screens/profile_screen.dart';

class FormularioUsuarioScreen extends StatelessWidget {
  FormularioUsuarioScreen({Key? key, required this.usuario}) : super(key: key);

  final Usuario usuario;
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nombreController = TextEditingController();
  TextEditingController _apellidosController = TextEditingController();
  TextEditingController _telefonoController = TextEditingController();
  TextEditingController _direccionController = TextEditingController();
  late PacienteRepository pacienteRepository;
  late MedicoRepository medicoRepository;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(245, 245, 245, 245),
      body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(top: 60),
                  child: Center(
                      child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15),
                        child: TextFormField(
                          controller: _nombreController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Nombre'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún nombre';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  )),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _apellidosController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Apellidos'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún texto';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _telefonoController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.phone),
                              suffixIconColor: Colors.white,
                              hintText: '   Teléfono'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún teléfono';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _direccionController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.location_city),
                              suffixIconColor: Colors.white,
                              hintText: '   Dirección'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce alguna dirección.';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 36),
                  child: Center(
                    child: SizedBox(
                      width: 300,
                      child: GestureDetector(
                        onTap: () {
                          if (_formKey.currentState!.validate()) {
                            if (usuario.rol.contains("PACIENTE")) {
                              final pacienteEditado = Usuario(
                                  nombre: _nombreController.text,
                                  apellidos: _apellidosController.text,
                                  direccion: _direccionController.text,
                                  telefono: _telefonoController.text,
                                  dni: usuario.dni,
                                  email: usuario.email,
                                  fechaNacimiento: usuario.fechaNacimiento,
                                  rol: usuario.rol);
                              pacienteRepository = PacienteRepositoryImpl();
                              pacienteRepository.editPaciente(pacienteEditado);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            } else {
                              final medicoEditado = MedicoEdit(
                                nombre: _nombreController.text,
                                apellidos: _apellidosController.text,
                                direccion: _direccionController.text,
                                telefono: _telefonoController.text,
                                dni: usuario.dni,
                                email: usuario.email,
                                especialidad: 1,
                                numColegiado: '222222222A',
                                password: 'daniel12',
                                password2: 'daniel12',
                              );
                              medicoRepository = MedicoRepositoryImpl();
                              medicoRepository.editMedico(medicoEditado);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            }
                          }
                        },
                        child: Container(
                          width: MediaQuery.of(context).size.width,
                          margin: const EdgeInsets.only(
                              top: 30, left: 30, right: 30),
                          padding: const EdgeInsets.symmetric(
                              horizontal: 50, vertical: 20),
                          decoration: BoxDecoration(
                              border: Border.all(color: Colors.black, width: 2),
                              borderRadius: BorderRadius.circular(50)),
                          child: Text(
                            'Editar'.toUpperCase(),
                            style: const TextStyle(color: Colors.black),
                            textAlign: TextAlign.center,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
          )),
    );
  }
}


/* 
class FormularioUsuarioScreen extends StatefulWidget {
  const FormularioUsuarioScreen({Key? key, required this.usuario})
      : super(key: key);

  final Usuario usuario;

  @override
  State<FormularioUsuarioScreen> createState() =>
      _FormularioUsuarioScreenState();
}

class _FormularioUsuarioScreenState extends State<FormularioUsuarioScreen> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nombreController = TextEditingController();
  TextEditingController _apellidosController = TextEditingController();
  TextEditingController _telefonoController = TextEditingController();
  TextEditingController _direccionController = TextEditingController();

  @override
  void initState() {
    super.initState();
    usuario
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(245, 245, 245, 245),
      body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(top: 60),
                  child: Center(
                      child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15),
                        child: TextFormField(
                          controller: _nombreController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Nombre'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún nombre';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  )),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _apellidosController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Apellidos'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún texto';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _telefonoController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.phone),
                              suffixIconColor: Colors.white,
                              hintText: '   Teléfono'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún teléfono';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _direccionController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.location_city),
                              suffixIconColor: Colors.white,
                              hintText: '   Dirección'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce alguna dirección.';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 36),
                  child: Center(
                    child: SizedBox(
                      width: 300,
                      child: GestureDetector(
                        onTap: () {
                          if (_formKey.currentState!.validate()) {
                            final usuarioEditado = Usuario(
                                nombre: _nombreController.text,
                                apellidos: _apellidosController.text,
                                direccion: _direccionController.text,
                                telefono: _telefonoController.text,
                                dni: usuario.dni,
                                email: usuario.email,
                                fechaNacimiento: usuario.fechaNacimiento,
                                rol: usuario.rol);

                            if (usuario.rol.contains("PACIENTE")) {
                              pacienteRepository = PacienteRepositoryImpl();
                              pacienteRepository.editPaciente(usuarioEditado);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            } else {
                              medicoRepository = MedicoRepositoryImpl();
                              medicoRepository.editMedico(usuarioEditado);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            }
                          }
                        },
                        child: Container(
                          width: MediaQuery.of(context).size.width,
                          margin: const EdgeInsets.only(
                              top: 30, left: 30, right: 30),
                          padding: const EdgeInsets.symmetric(
                              horizontal: 50, vertical: 20),
                          decoration: BoxDecoration(
                              border: Border.all(color: Colors.black, width: 2),
                              borderRadius: BorderRadius.circular(50)),
                          child: Text(
                            'Editar'.toUpperCase(),
                            style: const TextStyle(color: Colors.black),
                            textAlign: TextAlign.center,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
          )),
    );
  }
}
 */