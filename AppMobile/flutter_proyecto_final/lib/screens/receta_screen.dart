import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';

class RecetaScreen extends StatefulWidget {
  const RecetaScreen({Key? key}) : super(key: key);

  @override
  State<RecetaScreen> createState() => _RecetaScreenState();
}

class _RecetaScreenState extends State<RecetaScreen> {
  late PacienteRepository pacienteRepository;
  late Future<List<Receta>> recetas;
  bool lightMode = true;

  @override
  void initState() {
    super.initState();
    pacienteRepository = PacienteRepositoryImpl();
    recetas = pacienteRepository.fetchRecetas();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.grey,
        body: Container(
            padding: const EdgeInsets.only(top: 10),
            color: _getBackgroundColor(),
            child: Center(
                child: FutureBuilder<List<Receta>>(
              future: recetas,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Container(
                        margin: const EdgeInsets.only(left: 10, bottom: 10),
                        child: const Text(
                          'Recetas',
                          style: TextStyle(color: Colors.white),
                        ),
                      ),
                      _recetaList(snapshot.data!)
                    ],
                  );
                } else if (snapshot.hasError) {
                  return Text('${snapshot.error}');
                }

                // By default, show a loading spinner.
                return const CircularProgressIndicator();
              },
            ))));
  }

  Widget _recetaList(List<Receta> recetas) {
    return SizedBox(
      height: 270,
      width: MediaQuery.of(context).size.width,
      child: ListView.builder(
        shrinkWrap: true,
        scrollDirection: Axis.vertical,
        itemCount: recetas.length,
        itemBuilder: (context, index) {
          return _receta(recetas.elementAt(index));
        },
      ),
    );
  }

  Widget _receta(Receta receta) {
    return Card(
      child: Column(
        children: [Text(receta.medicamento.nombre.toString())],
      ),
    );
  }

  _getBackgroundColor() {
    return lightMode
        ? Color.fromARGB(255, 255, 255, 255)
        : const Color(0xfff1f1f1);
  }
}
