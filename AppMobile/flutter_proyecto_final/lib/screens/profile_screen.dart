import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository_imp.dart';
import 'package:flutter_proyecto_final/screens/login_screen.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  late AuthRepository authRepository;
  late Future<Usuario> usuario;

  @override
  void initState() {
    super.initState();
    authRepository = AuthRepositoryImpl();
    usuario = authRepository.fetchUsuario();
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
        padding: const EdgeInsets.fromLTRB(10.0, 0, 10.0, 0),
        child: ListView(
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                SizedBox(
                  width: 300,
                  height: 125,
                  child: Padding(
                    padding: const EdgeInsets.only(left: 40),
                    child: /* Image.network(usuario.avatar
                        .toString()), */
                        Image.asset('assets/images/iconAdmin.png'),
                  ),
                ),
              ],
            ),
            Container(height: 15.0),
            const Padding(
              padding: EdgeInsets.all(5.0),
            ),
            ListTile(
              title: const Text(
                "Nombre",
                style: TextStyle(
                  fontSize: 17,
                  fontWeight: FontWeight.w700,
                ),
              ),
              subtitle: Text(
                usuario.nombre + ' ' + usuario.apellidos,
              ),
              /*  trailing: IconButton(
                icon: const Icon(
                  Icons.edit,
                  size: 20.0,
                ),
                onPressed: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => FormularioUsuarioScreen(
                                usuario: usuario,
                              )));
                },
                tooltip: "Edit",
              ), */
            ),
            ListTile(
              title: const Text(
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
              title: const Text(
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
              title: const Text(
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
              title: const Text(
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
              title: const Text(
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
            Padding(
              padding: const EdgeInsets.only(top: 15, bottom: 15),
              child: Center(
                child: SizedBox(
                  width: 300,
                  child: GestureDetector(
                    onTap: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => const LoginScreen()));
                    },
                    child: Container(
                      width: MediaQuery.of(context).size.width,
                      margin:
                          const EdgeInsets.only(top: 30, left: 30, right: 30),
                      padding: const EdgeInsets.symmetric(
                          horizontal: 50, vertical: 20),
                      decoration: BoxDecoration(
                          border: Border.all(color: Colors.black, width: 2),
                          borderRadius: BorderRadius.circular(50)),
                      child: Text(
                        'Cerrar sesión'.toUpperCase(),
                        style: const TextStyle(color: Colors.black),
                        textAlign: TextAlign.center,
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
