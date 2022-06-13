import 'package:flutter_proyecto_final/models/medicamento_response.dart';
import 'package:flutter_proyecto_final/models/medico_response.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';

class Receta {
  Receta({
    required this.fechaInicio,
    required this.fechaFin,
    required this.diasDeTomas,
    required this.momentosDeTomas,
    required this.medicamento,
    required this.medico,
    required this.paciente,
  });
  late final String fechaInicio;
  late final String fechaFin;
  late final List<String> diasDeTomas;
  late final List<String> momentosDeTomas;
  late final Medicamento medicamento;
  late final Medico medico;
  late final Paciente paciente;

  Receta.fromJson(Map<String, dynamic> json) {
    fechaInicio = json['fechaInicio'];
    fechaFin = json['fechaFin'];
    diasDeTomas = List.castFrom<dynamic, String>(json['diasDeTomas']);
    momentosDeTomas = List.castFrom<dynamic, String>(json['momentosDeTomas']);
    medicamento = Medicamento.fromJson(json['medicamento']);
    medico = Medico.fromJson(json['medico']);
    paciente = Paciente.fromJson(json['paciente']);
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['fechaInicio'] = fechaInicio;
    _data['fechaFin'] = fechaFin;
    _data['diasDeTomas'] = diasDeTomas;
    _data['momentosDeTomas'] = momentosDeTomas;
    _data['medicamento'] = medicamento.toJson();
    _data['medico'] = medico.toJson();
    _data['paciente'] = paciente.toJson();
    return _data;
  }
}
