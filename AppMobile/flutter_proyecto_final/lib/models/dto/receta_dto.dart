class RecetaDto {
  RecetaDto({
    required this.fechaInicio,
    required this.fechaFin,
    required this.diasDeTomas,
    required this.momentosDeTomas,
    required this.idMedicamento,
    required this.idPaciente,
  });
  late final String fechaInicio;
  late final String fechaFin;
  late final List<String> diasDeTomas;
  late final List<String> momentosDeTomas;
  late final String idMedicamento;
  late final String idPaciente;

  RecetaDto.fromJson(Map<String, dynamic> json) {
    fechaInicio = json['fechaInicio'];
    fechaFin = json['fechaFin'];
    diasDeTomas = List.castFrom<dynamic, String>(json['diasDeTomas']);
    momentosDeTomas = List.castFrom<dynamic, String>(json['momentosDeTomas']);
    idMedicamento = json['idMedicamento'];
    idPaciente = json['idPaciente'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['fechaInicio'] = fechaInicio;
    _data['fechaFin'] = fechaFin;
    _data['diasDeTomas'] = diasDeTomas;
    _data['momentosDeTomas'] = momentosDeTomas;
    _data['idMedicamento'] = idMedicamento;
    _data['idPaciente'] = idPaciente;
    return _data;
  }
}
