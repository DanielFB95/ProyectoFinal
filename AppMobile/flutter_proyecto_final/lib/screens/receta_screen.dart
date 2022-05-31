import 'package:flutter/material.dart';

class MyWidget extends StatefulWidget {
  const MyWidget({Key? key}) : super(key: key);

  @override
  State<MyWidget> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  @override
  Widget build(BuildContext context) {
    return Scaffold();
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
