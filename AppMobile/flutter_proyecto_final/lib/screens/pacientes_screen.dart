import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';

class PacientesScreen extends StatefulWidget {
  const PacientesScreen({Key? key}) : super(key: key);

  @override
  State<PacientesScreen> createState() => _PacientesScreenState();
}

class _PacientesScreenState extends State<PacientesScreen> {
  late PacienteRepository pacienteRepository;
  late Future<List<Paciente>> pacientes;
  bool lightMode = true;

  @override
  void initState() {
    super.initState();
    pacienteRepository = PacienteRepositoryImpl();
    pacientes = pacienteRepository.fetchPacientes();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.grey,
        body: Container(
            padding: const EdgeInsets.only(top: 10),
            color: _getBackgroundColor(),
            child: Center(
                child: FutureBuilder<List<Paciente>>(
              future: pacientes,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Container(
                        margin: const EdgeInsets.only(left: 10, bottom: 10),
                        child: const Text(
                          'People',
                          style: TextStyle(
                              color: Colors.white, fontFamily: 'Jedi'),
                        ),
                      ),
                      _pacienteList(snapshot.data!)
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

  Widget _pacienteList(List<Paciente> peopleList) {
    return SizedBox(
      height: 270,
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
    return Card(
      child: Column(
        children: [
          const Text('Nombre'),
          Text(paciente.nombre + ' ' + paciente.apellidos),
          Text(paciente.direccion),
          Text(paciente.email),
          Text(paciente.dni)
        ],
      ),
    );
  }

  _getBackgroundColor() {
    return lightMode
        ? Color.fromARGB(255, 255, 255, 255)
        : const Color(0xfff1f1f1);
  }
}
