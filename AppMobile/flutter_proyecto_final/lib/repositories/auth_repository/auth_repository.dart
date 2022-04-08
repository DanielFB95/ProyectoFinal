import 'package:flutter_proyecto_final/models/dto/login_dto.dart';
import 'package:flutter_proyecto_final/models/login_response.dart';

abstract class AuthRepository {
  Future<LoginResponse> login(LoginDto loginDto);

  //Future<SignUpResponse> signUp(SignUp signUp, String file);
}
