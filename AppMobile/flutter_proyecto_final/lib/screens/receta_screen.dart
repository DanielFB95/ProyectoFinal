// ignore_for_file: unused_element

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
        body: SingleChildScrollView(
      child: Container(
          height: MediaQuery.of(context).size.height,
          padding: const EdgeInsets.only(top: 10),
          decoration: const BoxDecoration(
              image: DecorationImage(
                  image: AssetImage("assets/images/fondo_tratamed.jpg"),
                  fit: BoxFit.cover)),
          child: Center(
              child: FutureBuilder<List<Receta>>(
            future: recetas,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      margin:
                          const EdgeInsets.only(top: 50, left: 10, bottom: 10),
                      child: const Center(
                        child: Text(
                          'RECETAS',
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 35,
                              fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                    _recetaList(snapshot.data!)
                  ],
                );
              } else if (snapshot.hasError) {
                return Text('${snapshot.error}');
              }
              return const CircularProgressIndicator();
            },
          ))),
    ));
  }

  Widget _recetaList(List<Receta> recetas) {
    return Padding(
      padding: const EdgeInsets.only(left: 30),
      child: SizedBox(
        width: 300,
        child: ListView.builder(
          shrinkWrap: true,
          scrollDirection: Axis.vertical,
          itemCount: recetas.length,
          itemBuilder: (context, index) {
            return _receta(recetas.elementAt(index));
          },
        ),
      ),
    );
  }

  Widget _receta(Receta receta) {
    return Container(
      height: 260,
      child: Card(
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
        margin: const EdgeInsets.all(15),
        elevation: 10,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Row(
              children: [
                Padding(padding: EdgeInsets.only(left: 15)),
                Column(
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(top: 10),
                      child: Text(
                        'Fecha de inicio',
                        style: TextStyle(
                            fontWeight: FontWeight.bold, color: Colors.red),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10),
                      child: Text(receta.fechaInicio),
                    ),
                  ],
                ),
                Padding(padding: EdgeInsets.only(left: 75)),
                Column(
                  children: [
                    const Padding(
                      padding: EdgeInsets.only(top: 10),
                      child: Text(
                        "Fecha fin",
                        style: TextStyle(
                            fontWeight: FontWeight.bold, color: Colors.red),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10),
                      child: Text(receta.fechaFin),
                    ),
                  ],
                ),
              ],
            ),
            const Padding(
              padding: EdgeInsets.only(top: 10),
              child: Text(
                "Días de toma",
                style:
                    TextStyle(fontWeight: FontWeight.bold, color: Colors.red),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 10),
              child: Text(receta.diasDeTomas
                  .toString()
                  .toLowerCase()
                  .replaceAll(r'[', '')
                  .replaceAll(r']', '')),
            ),
            const Padding(
                padding: EdgeInsets.only(top: 10),
                child: Text(
                  "Momentos de tomas",
                  style:
                      TextStyle(fontWeight: FontWeight.bold, color: Colors.red),
                )),
            Padding(
              padding: const EdgeInsets.only(top: 10),
              child: Text(receta.momentosDeTomas
                  .toString()
                  .toLowerCase()
                  .replaceAll(r'[', '')
                  .replaceAll(r']', '')),
            ),
            const Padding(
              padding: EdgeInsets.only(top: 10),
              child: Text(
                "Medicamento",
                style:
                    TextStyle(fontWeight: FontWeight.bold, color: Colors.red),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 10, bottom: 15),
              child: Text(receta.medicamento.nombre),
            ),
          ],
        ),
      ),
    );
  }

  _getBackgroundColor() {
    return lightMode
        ? const Color.fromARGB(255, 255, 255, 255)
        : const Color(0xfff1f1f1);
  }
}
