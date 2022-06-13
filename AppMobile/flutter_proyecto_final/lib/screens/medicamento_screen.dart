import 'package:flutter/material.dart';

class MedicamentoScreen extends StatefulWidget {
  const MedicamentoScreen({Key? key}) : super(key: key);

  @override
  State<MedicamentoScreen> createState() => _MedicamentoScreenState();
}

class _MedicamentoScreenState extends State<MedicamentoScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(padding: EdgeInsets.only(top: 75), child: _receta()),
    );
  }

  Widget _receta() {
    return Padding(
      padding: const EdgeInsets.all(1),
      child: SizedBox(
        width: 200,
        height: 200,
        child: Column(
          children: [Text("Medicamento")],
        ),
      ),
    );
  }
}
