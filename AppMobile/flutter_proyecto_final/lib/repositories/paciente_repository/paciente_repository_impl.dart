import 'dart:convert';

import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:http/http.dart';

class PacienteRepositoryImpl extends PacienteRepository {
  final Client _client = Client();

  @override
  Future<List<Paciente>> fetchPacientes() async {
    var token = PreferenceUtils.getString("token");

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client
        .get(Uri.parse('${Constant.URL_API_BASE}/paciente/'), headers: headers);

    if (response.statusCode == 200) {
      return PacienteResponse.fromJson(json.decode(response.body)).result;
    } else {
      throw Exception('Fail to load person');
    }
  }
}
