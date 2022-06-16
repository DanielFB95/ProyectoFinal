import 'dart:convert';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';
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
      return PacienteResponse.fromJson(
              json.decode(utf8.decode(response.bodyBytes)))
          .result;
    } else {
      throw Exception('Fállo al cargar la lista de pacientes.');
    }
  }

  @override
  Future<List<Receta>> fetchRecetas() async {
    var token = PreferenceUtils.getString("token");
    var id = PreferenceUtils.getString("idPaciente");

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.get(
        Uri.parse('${Constant.URL_API_BASE}/paciente/recetas/$id'),
        headers: headers);

    if (response.statusCode == 200) {
      return (List.from(json.decode(utf8.decode(response.bodyBytes)))
          .map((e) => Receta.fromJson(e))
          .toList());
    } else {
      throw Exception('Fállo al cargar recetas');
    }
  }

  @override
  Future<Paciente> editPaciente(Usuario usuario) async {
    var token = PreferenceUtils.getString("token");
    var id = PreferenceUtils.getString("idPaciente");

    var body = json.encode({
      "nombre": usuario.nombre,
      "apellidos": usuario.apellidos,
      "email": usuario.email,
      "telefono": usuario.telefono,
      "dni": usuario.dni,
      "direccion": usuario.direccion
    });

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.put(
        Uri.parse('${Constant.URL_API_BASE}/paciente/$id'),
        headers: headers,
        body: body);

    if (response.statusCode == 200) {
      return Paciente.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fállo al cargar el paciente');
    }
  }
}
