class UsuarioDto {
  UsuarioDto({
    required this.nombre,
    required this.apellidos,
    required this.telefono,
    required this.direccion,
  });
  late final String nombre;
  late final String apellidos;
  late final String telefono;
  late final String direccion;

  UsuarioDto.fromJson(Map<String, dynamic> json) {
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    telefono = json['telefono'];
    direccion = json['direccion'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['telefono'] = telefono;
    _data['direccion'] = direccion;
    return _data;
  }
}
