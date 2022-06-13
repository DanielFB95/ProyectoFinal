import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/paciente_response.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  late PacienteRepository pacienteRepository;
  late Future<Paciente> paciente;

  @override
  void initState() {
    super.initState();
    pacienteRepository = PacienteRepositoryImpl();
    paciente = pacienteRepository.fetchPaciente();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
          padding: EdgeInsets.only(top: 75),
          child: Container(
              margin: const EdgeInsets.only(bottom: 50),
              height: 200,
              child: FutureBuilder<Paciente>(
                future: paciente,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return _perfilPaciente(snapshot.data!);
                  } else if (snapshot.hasError) {
                    return Text('${snapshot.error}');
                  }
                  return const CircularProgressIndicator();
                },
              ))),
    );
  }

  Widget _perfilPaciente(Paciente paciente) {
    return Padding(
      padding: const EdgeInsets.all(1),
      child: SizedBox(
        width: 200,
        height: 200,
        child: Column(
          children: [Text(paciente.nombre)],
        ),
      ),
    );
  }
}
