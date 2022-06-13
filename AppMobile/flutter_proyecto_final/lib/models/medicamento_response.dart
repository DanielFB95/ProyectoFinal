class Medicamento {
  Medicamento({
    required this.id,
    required this.nombre,
    required this.descripcion,
  });
  late final int id;
  late final String nombre;
  late final String descripcion;

  Medicamento.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    descripcion = json['descripcion'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['descripcion'] = descripcion;
    return _data;
  }
}
