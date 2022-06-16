import 'dart:convert';

import 'package:flutter_proyecto_final/models/dto/sign_up_dto.dart';
import 'package:flutter_proyecto_final/models/login_response.dart';
import 'package:flutter_proyecto_final/models/dto/login_dto.dart';
import 'package:flutter_proyecto_final/models/sign_up_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';
import 'package:flutter_proyecto_final/repositories/auth_repository/auth_repository.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:http/http.dart' as http;

class AuthRepositoryImpl extends AuthRepository {
  @override
  Future<LoginResponse> login(LoginDto loginDto) async {
    //var token = PreferenceUtils.getString("token");
    Map<String, String> headers = {
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer $token'
    };

    final response = await http.post(
        Uri.parse('${Constant.URL_API_BASE}/auth/login'),
        headers: headers,
        encoding: Encoding.getByName("utf-8"),
        body: jsonEncode(loginDto.toJson()));

    if (response.statusCode == 201) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fail to login');
    }
  }

  @override
  Future<SignUpResponse> signUp(SignUpDto signUpDto) async {
    throw UnimplementedError();
  }

  @override
  Future<Usuario> fetchUsuario() async {
    var token = PreferenceUtils.getString("token");

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };
    final response = await http
        .get(Uri.parse('${Constant.URL_API_BASE}/auth/me'), headers: headers);

    if (response.statusCode == 200) {
      return Usuario.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fállo al cargar paciente.');
    }
  }
}
