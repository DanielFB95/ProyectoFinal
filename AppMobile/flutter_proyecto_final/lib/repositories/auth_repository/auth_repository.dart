import 'package:flutter_proyecto_final/models/dto/login_dto.dart';
import 'package:flutter_proyecto_final/models/dto/sign_up_dto.dart';
import 'package:flutter_proyecto_final/models/login_response.dart';
import 'package:flutter_proyecto_final/models/sign_up_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';

abstract class AuthRepository {
  Future<LoginResponse> login(LoginDto loginDto);

  Future<SignUpResponse> signUp(SignUpDto signUpDto);

  Future<Usuario> fetchUsuario();
}
