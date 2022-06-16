// ignore_for_file: unused_import, unused_element, prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';
import 'package:flutter_proyecto_final/screens/formulario_receta_screen.dart';
import 'package:flutter_proyecto_final/screens/menu_medico_screen.dart';
import 'package:flutter_proyecto_final/screens/pacientes_screen.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';

class PacienteScreen extends StatefulWidget {
  const PacienteScreen({Key? key, required this.paciente}) : super(key: key);

  final Paciente paciente;

  @override
  State<PacienteScreen> createState() => _PacienteScreenState();
}

class _PacienteScreenState extends State<PacienteScreen> {
  late PacienteRepository pacienteRepository;
  late Future<List<Receta>> recetas;
  bool lightMode = true;

  @override
  void initState() {
    super.initState();
    PreferenceUtils.setString("idPaciente", widget.paciente.id);
    pacienteRepository = PacienteRepositoryImpl();
    recetas = pacienteRepository.fetchRecetas();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SingleChildScrollView(
      child: Container(
          height: MediaQuery.of(context).size.height,
          decoration: BoxDecoration(
              image: const DecorationImage(
                  image: AssetImage("assets/images/fondo_tratamed.jpg"),
                  fit: BoxFit.cover)),
          child: SizedBox(
            child: Center(
              child: FutureBuilder<List<Receta>>(
                future: recetas,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(children: [
                          Padding(
                            padding: const EdgeInsets.only(top: 35, right: 250),
                            child: IconButton(
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            const MenuMedicoScreen()));
                              },
                              icon: const Icon(
                                Icons.keyboard_return,
                                size: 50,
                              ),
                              color: Colors.white,
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(top: 35),
                            child: IconButton(
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            FormularioRecetaScreen(
                                                paciente: widget.paciente)));
                              },
                              icon: const Icon(
                                Icons.add,
                                size: 50,
                              ),
                              color: Colors.white,
                            ),
                          ),
                        ]),
                        Container(
                          margin: const EdgeInsets.only(left: 10, bottom: 10),
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
              ),
            ),
          )),
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
    return SizedBox(
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
