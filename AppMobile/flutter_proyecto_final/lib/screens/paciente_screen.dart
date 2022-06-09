import 'package:flutter/material.dart';

class PacienteScreen extends StatefulWidget {
  const PacienteScreen({Key? key}) : super(key: key);

  @override
  State<PacienteScreen> createState() => _PacienteScreenState();
}

class _PacienteScreenState extends State<PacienteScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: EdgeInsets.only(top: 75),
        child: Text("Paciente screen"),
      ),
    );
  }
}
