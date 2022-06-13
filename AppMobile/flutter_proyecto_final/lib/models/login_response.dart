class LoginResponse {
  LoginResponse({
    required this.id,
    required this.nombre,
    required this.apellidos,
    required this.telefono,
    required this.email,
    required this.dni,
    required this.direccion,
    required this.avatar,
    required this.fechaNacimiento,
    required this.rol,
    required this.token,
  });
  late final String id;
  late final String nombre;
  late final String apellidos;
  late final String telefono;
  late final String email;
  late final String dni;
  late final String direccion;
  late final String avatar;
  late final String fechaNacimiento;
  late final String rol;
  late final String token;

  LoginResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    telefono = json['telefono'];
    email = json['email'];
    dni = json['dni'];
    direccion = json['direccion'];
    avatar = json['avatar'];
    fechaNacimiento = json['fechaNacimiento'];
    rol = json['rol'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
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
