import 'dart:convert';

import 'package:flutter_proyecto_final/models/medicamento_response.dart';
import 'package:flutter_proyecto_final/repositories/medicamento_repository/medicamento_repository.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:http/http.dart';

class MedicamentoRepositoryImpl extends MedicamentoRepository {
  final Client _client = Client();

  @override
  Future<List<Medicamento>> fetchMedicamentos() async {
    var token = PreferenceUtils.getString("token");

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.get(
        Uri.parse('${Constant.URL_API_BASE}/medicamento/'),
        headers: headers);

    if (response.statusCode == 200) {
      return MedicamentoResponse.fromJson(json.decode(response.body)).content;
    } else {
      throw Exception('Fállo al cargar la lista de medicamentos.');
    }
  }
}
