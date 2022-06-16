// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_proyecto_final/blocs/login_bloc/login_bloc.dart';
import 'package:flutter_proyecto_final/models/dto/login_dto.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository_imp.dart';
import 'package:flutter_proyecto_final/screens/menu_medico_screen.dart';
import 'package:flutter_proyecto_final/screens/menu_paciente_screen.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _formKey = GlobalKey<FormState>();
  late AuthRepository authRepository;
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  late bool _passVisibility;

  @override
  void initState() {
    super.initState();
    authRepository = AuthRepositoryImpl();
    _passVisibility = true;
    PreferenceUtils.init();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) {
        return LoginBloc(authRepository);
      },
      child: _createBody(context),
    );
  }

  _createBody(BuildContext context) {
    return Scaffold(
      body: Center(
        child: BlocConsumer<LoginBloc, LoginState>(
          listenWhen: (context, state) {
            return state is LoginSuccessState || state is LoginErrorState;
          },
          listener: (context, state) {
            if (state is LoginSuccessState) {
              PreferenceUtils.setString("token", state.loginResponse.token);

              if (state.loginResponse.rol.contains("PACIENTE")) {
                PreferenceUtils.setString("idPaciente", state.loginResponse.id);
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const MenuPacienteScreen()));
              } else {
                PreferenceUtils.setString("idMedico", state.loginResponse.id);
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const MenuMedicoScreen()));
              }
            } else if (state is LoginErrorState) {
              _showSnackbar(context, state.message);
            }
          },
          buildWhen: (context, state) {
            return state is LoginInitialState || state is LoginLoadingState;
          },
          builder: (context, state) {
            if (state is LoginInitialState) {
              return form(context);
            } else if (state is LoginLoadingState) {
              return const Center(child: CircularProgressIndicator());
            } else {
              return form(context);
            }
          },
        ),
      ),
    );
  }

  void _showSnackbar(BuildContext context, String message) {
    final snackBar = SnackBar(content: Text(message));
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  Widget form(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Padding(padding: EdgeInsets.only(top: 75)),
                Container(
                  padding: const EdgeInsets.all(5),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(100),
                  ),
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(10),
                    child: Image.asset(
                      'assets/images/logo_tratamed.png',
                    ),
                  ),
                ),
                const Padding(padding: EdgeInsets.only(top: 25)),
                Padding(
                  padding: const EdgeInsets.only(top: 10),
                  child: Center(
                      child: Card(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10)),
                    margin: EdgeInsets.all(15),
                    elevation: 10,
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15, left: 15),
                        child: TextFormField(
                          style: TextStyle(fontSize: 20),
                          controller: emailController,
                          decoration: const InputDecoration(
                            border: InputBorder.none,
                            suffixIcon: Icon(Icons.email),
                            suffixIconColor: Colors.white,
                            hintText: ' Email',
                          ),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún correo';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  )),
                ),
                Center(
                  child: Card(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10)),
                    margin: EdgeInsets.all(15),
                    elevation: 10,
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0, left: 15),
                        child: TextFormField(
                          style: TextStyle(fontSize: 20),
                          controller: passwordController,
                          obscureText: _passVisibility,
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              suffixIcon: IconButton(
                                icon: _passVisibility
                                    ? Icon(Icons.visibility_off)
                                    : Icon(Icons.visibility),
                                onPressed: () {
                                  setState(() {
                                    _passVisibility = !_passVisibility;
                                  });
                                },
                              ),
                              suffixIconColor: Colors.white,
                              hintText: ' Contraseña'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún texto';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 36),
                  child: Center(
                    child: SizedBox(
                      width: 300,
                      child: GestureDetector(
                        onTap: () {
                          if (_formKey.currentState!.validate()) {
                            final loginDto = LoginDto(
                                email: emailController.text,
                                password: passwordController.text);
                            BlocProvider.of<LoginBloc>(context)
                                .add(DoLoginEvent(loginDto));
                          }
                        },
                        child: Card(
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(150)),
                          margin: EdgeInsets.all(15),
                          elevation: 10,
                          child: Container(
                            width: MediaQuery.of(context).size.width,
                            padding: const EdgeInsets.symmetric(
                                horizontal: 50, vertical: 20),
                            decoration: BoxDecoration(
                                color: Color.fromARGB(255, 36, 175, 185),
                                border: Border.all(color: Colors.red, width: 5),
                                borderRadius: BorderRadius.circular(50)),
                            child: Text(
                              'Login'.toUpperCase(),
                              style: const TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 20),
                              textAlign: TextAlign.center,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
          )),
    );
  }
}
