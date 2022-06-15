import 'dart:convert';

import 'package:flutter_proyecto_final/models/dto/receta_dto.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';
import 'package:flutter_proyecto_final/repositories/recetaRepository/receta_repository.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:http/http.dart';

class RecetaRepositoryImpl extends RecetaRepository {
  final Client _client = Client();

  @override
  Future<Receta> nuevaReceta(RecetaDto recetaDto) async {
    var token = PreferenceUtils.getString("token");

    var body = json.encode({
      "fechaInicio": recetaDto.fechaInicio,
      "fechaFin": recetaDto.fechaInicio,
      "diasDeTomas": recetaDto.diasDeTomas,
      "momentosDeTomas": recetaDto.momentosDeTomas,
      "idMedicamento": recetaDto.idMedicamento,
      "idPaciente": recetaDto.idPaciente
    });

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    final response = await _client.post(
        Uri.parse('${Constant.URL_API_BASE}/receta/'),
        headers: headers,
        body: body);

    if (response.statusCode == 201) {
      return Receta.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fállo al crear nueva receta');
    }
  }
}
