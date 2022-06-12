import 'package:flutter/material.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(padding: EdgeInsets.only(top: 75), child: _perfil()),
    );
  }

  Widget _perfil() {
    return Padding(
      padding: const EdgeInsets.all(1),
      child: SizedBox(
        width: 200,
        height: 200,
        child: Column(
          children: [Text("Perfil")],
        ),
      ),
    );
  }
}
