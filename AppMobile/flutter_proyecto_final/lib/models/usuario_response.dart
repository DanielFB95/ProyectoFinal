// ignore_for_file: prefer_void_to_null

class Usuario {
  Usuario({
    required this.nombre,
    required this.apellidos,
    required this.telefono,
    required this.email,
    required this.dni,
    required this.direccion,
    this.avatar,
    required this.fechaNacimiento,
    required this.rol,
    this.token,
  });
  late final String nombre;
  late final String apellidos;
  late final String telefono;
  late final String email;
  late final String dni;
  late final String direccion;
  late final Null avatar;
  late final String fechaNacimiento;
  late final String rol;
  late final Null token;

  Usuario.fromJson(Map<String, dynamic> json) {
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    telefono = json['telefono'];
    email = json['email'];
    dni = json['dni'];
    direccion = json['direccion'];
    avatar = null;
    fechaNacimiento = json['fechaNacimiento'];
    rol = json['rol'];
    token = null;
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['telefono'] = telefono;
    _data['email'] = email;
    _data['dni'] = dni;
    _data['direccion'] = direccion;
    _data['avatar'] = avatar;
    _data['fechaNacimiento'] = fechaNacimiento;
    _data['rol'] = rol;
    _data['token'] = token;
    return _data;
  }
}
