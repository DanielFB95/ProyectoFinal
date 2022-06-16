import 'dart:convert';

import 'package:flutter_proyecto_final/models/medico_edit.dart';
import 'package:flutter_proyecto_final/models/medico_response.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:http/http.dart';

class MedicoRepositoryImpl extends MedicoRepository {
  final Client _client = Client();

  @override
  Future<List<Paciente>> fetchPacientes() async {
    var token = PreferenceUtils.getString("token");
    var id = PreferenceUtils.getString("idMedico");

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.get(
        Uri.parse('${Constant.URL_API_BASE}/medico/pacientes/$id'),
        headers: headers);

    if (response.statusCode == 200) {
      return (List.from(json.decode(response.body))
          .map((e) => Paciente.fromJson(e))
          .toList());
    } else {
      throw Exception('Fállo al cargar pacientes');
    }
  }

  @override
  Future<Medico> editMedico(MedicoEdit medicoEdit) async {
    var token = PreferenceUtils.getString("token");
    var id = PreferenceUtils.getString("idMedico");

    var body = json.encode({
      "nombre": medicoEdit.nombre,
      "apellidos": medicoEdit.apellidos,
      "numColegiado": medicoEdit.numColegiado,
      "telefono": medicoEdit.telefono,
      "dni": medicoEdit.dni,
      "direccion": medicoEdit.direccion,
      "especialidad": medicoEdit.especialidad
    });

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.put(
        Uri.parse('${Constant.URL_API_BASE}/medico/$id'),
        headers: headers,
        body: body);

    if (response.statusCode == 200) {
      return Medico.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fállo al cargar el médico.');
    }
  }
}
