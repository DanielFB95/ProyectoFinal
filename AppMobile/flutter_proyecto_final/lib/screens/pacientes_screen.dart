// ignore_for_file: unused_element

import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository_impl.dart';
import 'package:flutter_proyecto_final/screens/paciente_screen.dart';

class PacientesScreen extends StatefulWidget {
  const PacientesScreen({Key? key}) : super(key: key);

  @override
  State<PacientesScreen> createState() => _PacientesScreenState();
}

class _PacientesScreenState extends State<PacientesScreen> {
  late MedicoRepository medicoRepository;
  late Future<List<Paciente>> pacientes;
  bool lightMode = true;

  @override
  void initState() {
    super.initState();
    medicoRepository = MedicoRepositoryImpl();
    pacientes = medicoRepository.fetchPacientes();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SingleChildScrollView(
      child: Container(
          decoration: const BoxDecoration(
              image: DecorationImage(
                  image: AssetImage("assets/images/fondo_tratamed.jpg"),
                  fit: BoxFit.cover)),
          height: MediaQuery.of(context).size.height,
          padding: const EdgeInsets.only(top: 10),
          child: Center(
              child: FutureBuilder<List<Paciente>>(
            future: pacientes,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Center(
                      child: Container(
                        margin: const EdgeInsets.only(top: 30),
                        child: const Text(
                          'PACIENTES',
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 35,
                              fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                    _pacienteList(snapshot.data!)
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

  Widget _pacienteList(List<Paciente> peopleList) {
    return SizedBox(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      child: ListView.builder(
        shrinkWrap: true,
        scrollDirection: Axis.vertical,
        itemCount: peopleList.length,
        itemBuilder: (context, index) {
          return _paciente(peopleList.elementAt(index));
        },
      ),
    );
  }

  Widget _paciente(Paciente paciente) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => PacienteScreen(paciente: paciente)));
      },
      child: Padding(
          padding: const EdgeInsets.only(left: 10, right: 10, bottom: 10),
          child: Card(
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
            margin: const EdgeInsets.all(15),
            elevation: 10,
            child: Center(
              child: Column(
                children: [
                  const Padding(
                    padding: EdgeInsets.only(top: 10),
                    child: Text(
                      'Nombre',
                      style: TextStyle(
                          fontWeight: FontWeight.bold, color: Colors.red),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 10),
                    child: Text(paciente.nombre + ' ' + paciente.apellidos),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(top: 10),
                    child: Text(
                      "Dirección",
                      style: TextStyle(
                          fontWeight: FontWeight.bold, color: Colors.red),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 10),
                    child: Text(paciente.direccion),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(top: 10),
                    child: Text(
                      "Email",
                      style: TextStyle(
                          fontWeight: FontWeight.bold, color: Colors.red),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 10),
                    child: Text(paciente.email),
                  ),
                  const Padding(
                      padding: EdgeInsets.only(top: 10),
                      child: Text(
                        "Dni",
                        style: TextStyle(
                            fontWeight: FontWeight.bold, color: Colors.red),
                      )),
                  Padding(
                    padding: const EdgeInsets.only(top: 10),
                    child: Text(paciente.dni),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(top: 10),
                    child: Text(
                      "Teléfono",
                      style: TextStyle(
                          fontWeight: FontWeight.bold, color: Colors.red),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 10),
                    child: Text(paciente.telefono),
                  ),
                  const Padding(
                    padding: EdgeInsets.only(top: 10),
                    child: Text(
                      "Fecha de nacimiento",
                      style: TextStyle(
                          fontWeight: FontWeight.bold, color: Colors.red),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 10, bottom: 15),
                    child: Text(paciente.fechaNacimiento),
                  ),
                ],
              ),
            ),
          )),
    );
  }

  _getBackgroundColor() {
    return lightMode
        ? const Color.fromARGB(255, 36, 175, 185)
        : const Color(0xfff1f1f1);
  }
}
