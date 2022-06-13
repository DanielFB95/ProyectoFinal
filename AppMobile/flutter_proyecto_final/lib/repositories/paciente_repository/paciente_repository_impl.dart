import 'dart:convert';

import 'package:flutter_proyecto_final/models/paciente_response.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:http/http.dart';

class PacienteRepositoryImpl extends PacienteRepository {
  final Client _client = Client();
  @override
  Future<Paciente> fetchPaciente() async {
    var token = PreferenceUtils.getString("token");

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.get(Uri.parse('${Constant.URL_API_BASE}/me'),
        headers: headers);

    if (response.statusCode == 200) {
      return Paciente.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fállo al cargar paciente.');
    }
  }
}
