import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_proyecto_final/blocs/sign_up_bloc/sign_up_bloc.dart';
import 'package:flutter_proyecto_final/models/dto/sign_up_dto.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository_imp.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({Key? key}) : super(key: key);

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  late AuthRepository authRepository;
  final _formKey = GlobalKey<FormState>();
  TextEditingController usernameController = TextEditingController();
  TextEditingController fullnameController = TextEditingController();
  TextEditingController emailControler = TextEditingController();
  TextEditingController birthdateController = TextEditingController();
  TextEditingController biographyController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController password2Controller = TextEditingController();

  late SignUpBloc _signUpBloc;
  late SignUpDto signUpDto;

  @override
  void initState() {
    super.initState();
    authRepository = AuthRepositoryImpl();
    _signUpBloc = SignUpBloc(authRepository);
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) {
        return SignUpBloc(authRepository);
      },
      child: _createBody(context),
    );
  }

  _createBody(BuildContext context) {
    return Scaffold(
      body: Center(
        child: BlocConsumer<SignUpBloc, SignUpState>(
          listenWhen: (context, state) {
            return state is SignUpSuccessState || state is SignUpLoadingState;
          },
          listener: (context, state) {
            if (state is SignUpSuccessState) {
              //Navigator.push(context,MaterialPageRoute(builder: (context) => const MenuScreen()));
            } else if (state is SignUpErrorState) {
              _showSnackBar(context, state.message);
            }
          },
          buildWhen: (context, state) {
            return state is SignUpInitialState || state is SignUpLoadingState;
          },
          builder: (context, state) {
            if (state is SignUpInitialState) {
              return form(context);
            } else if (state is SignUpLoadingState) {
              return const Center(child: CircularProgressIndicator());
            } else {
              return form(context);
            }
          },
        ),
      ),
    );
  }

  void _showSnackBar(BuildContext context, String message) {
    final snackbar = SnackBar(content: Text(message));
    ScaffoldMessenger.of(context).showSnackBar(snackbar);
  }

  Widget form(BuildContext context) {
    return SingleChildScrollView(
      child: Form(
        key: _formKey,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(padding: EdgeInsets.only(top: 75)),
            Center(
              child: SizedBox(
                width: 125,
                //child: Image.asset('', width: 100,fit: BoxFit.cover,),
              ),
            ),
            Padding(padding: EdgeInsets.only(top: 25)),
            Center(
              child: Text(
                'Creación de una',
                style: TextStyle(fontSize: 20, color: Colors.black54),
              ),
            ),
            Center(
              child: Text(
                'nueva cuenta.',
                style: TextStyle(fontSize: 20, color: Colors.black54),
              ),
            ),
            Padding(
              padding: EdgeInsets.only(top: 35),
              child: Center(
                child: Card(
                  child: SizedBox(
                    width: 300,
                    child: Padding(
                      padding: EdgeInsets.only(top: 15),
                      child: TextFormField(
                        controller: usernameController,
                        decoration: const InputDecoration(
                            hintText: 'Nombre de usuario'),
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Por favor introduzca algún texto';
                          }
                          return null;
                        },
                      ),
                    ),
                  ),
<<<<<<< HEAD
                ),
              ),
            ),
            Center(
                child: Card(
              child: SizedBox(
                width: 300,
                child: Padding(
                  padding: EdgeInsets.only(top: 15),
                  child: TextFormField(
                    controller: fullnameController,
                    decoration:
                        const InputDecoration(hintText: 'Nombre completo'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Por favor introduzca su nombre';
                      }
                      return null;
                    },
                  ),
                ),
              ),
            ))
=======
                )
              ),
              Center(
                child: Card(
                  child: SizedBox(
                    width: 300,
                    child: Padding(padding: EdgeInsets.only(top: 15),
                      child: TextFormField(
                        controller: emailControler,
                        decoration: const InputDecoration(hintText: 'E-mail'),
                      ),
                    ),
                  ),
                ),
              )
>>>>>>> b8c1a75b8a92c948e1fd272afc53b503d6cefcb2
          ],
        ),
      ),
    );
  }
}
