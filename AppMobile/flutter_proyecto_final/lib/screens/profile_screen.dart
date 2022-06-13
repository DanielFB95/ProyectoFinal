import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/paciente_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';
import 'package:flutter_proyecto_final/providers/app_provider.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository_imp.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';
import 'package:flutter_proyecto_final/screens/login_screen.dart';
import 'package:flutter_proyecto_final/screens/menu_screen.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:provider/provider.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  late PacienteRepository pacienteRepository;
  late Future<Paciente> paciente;
  late AuthRepository authRepository;
  late Future<Usuario> usuario;

  @override
  void initState() {
    super.initState();
    authRepository = AuthRepositoryImpl();
    usuario = authRepository.fetchUsuario();
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
          padding: const EdgeInsets.only(top: 75),
          child: Container(
              child: FutureBuilder<Usuario>(
            future: usuario,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return _profile(snapshot.data!);
              } else if (snapshot.hasError) {
                return Text('${snapshot.error}');
              }
              return const CircularProgressIndicator();
            },
          ))),
    );
  }

  Widget _profile(Usuario usuario) {
    return Scaffold(
      body: Padding(
        padding: EdgeInsets.fromLTRB(10.0, 0, 10.0, 0),
        child: ListView(
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Container(
                  width: 300,
                  height: 125,
                  child: Padding(
                    padding: EdgeInsets.only(left: 40),
                    child: /* Image.network(usuario.avatar
                        .toString()), */
                        Image.asset('assets/images/iconAdmin.png'),
                  ),
                ),
              ],
            ),
            Container(height: 15.0),
            Padding(
              padding: EdgeInsets.all(5.0),
            ),
            ListTile(
              title: Text(
                "Nombre",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.nombre + ' ' + usuario.apellidos,
              ),
              trailing: IconButton(
                icon: Icon(
                  Icons.edit,
                  size: 20.0,
                ),
                onPressed: () {},
                tooltip: "Edit",
              ),
            ),
            ListTile(
              title: Text(
                "Email",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.email,
              ),
            ),
            ListTile(
              title: Text(
                "Teléfono",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.telefono,
              ),
            ),
            ListTile(
              title: Text(
                "Dirección",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.direccion,
              ),
            ),
            ListTile(
              title: Text(
                "Dni",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.dni,
              ),
            ),
            ListTile(
              title: Text(
                "Fecha de nacimiento",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.fechaNacimiento,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _perfilUsuario(Usuario usuario) {
    return Padding(
      padding: const EdgeInsets.all(1),
      child: SizedBox(
        width: 200,
        height: 200,
        child: Column(
          children: [Text(usuario.nombre)],
        ),
      ),
    );
  }
}
