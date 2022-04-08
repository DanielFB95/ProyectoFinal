import 'package:flutter_proyecto_final/models/login_response.dart';
import 'package:flutter_proyecto_final/models/dto/login_dto.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository.dart';

class AuthRepositoryImpl extends AuthRepository {
  @override
  Future<LoginResponse> login(LoginDto loginDto) async {
    //var token = PreferencesUtils.getString("token");
    throw UnimplementedError();
  }
}
