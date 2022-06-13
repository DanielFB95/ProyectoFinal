class PacienteResponse {
  PacienteResponse({
    required this.result,
    required this.pageable,
    required this.last,
    required this.totalElements,
    required this.totalPages,
    required this.size,
    required this.number,
    required this.sort,
    required this.first,
    required this.numberOfElements,
    required this.empty,
  });
  late final List<Paciente> result;
  late final Pageable pageable;
  late final bool last;
  late final int totalElements;
  late final int totalPages;
  late final int size;
  late final int number;
  late final Sort sort;
  late final bool first;
  late final int numberOfElements;
  late final bool empty;

  PacienteResponse.fromJson(Map<String, dynamic> json) {
    result =
        List.from(json['content']).map((e) => Paciente.fromJson(e)).toList();
    pageable = Pageable.fromJson(json['pageable']);
    last = json['last'];
    totalElements = json['totalElements'];
    totalPages = json['totalPages'];
    size = json['size'];
    number = json['number'];
    sort = Sort.fromJson(json['sort']);
    first = json['first'];
    numberOfElements = json['numberOfElements'];
    empty = json['empty'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['content'] = result.map((e) => e.toJson()).toList();
    _data['pageable'] = pageable.toJson();
    _data['last'] = last;
    _data['totalElements'] = totalElements;
    _data['totalPages'] = totalPages;
    _data['size'] = size;
    _data['number'] = number;
    _data['sort'] = sort.toJson();
    _data['first'] = first;
    _data['numberOfElements'] = numberOfElements;
    _data['empty'] = empty;
    return _data;
  }
}

class Paciente {
  Paciente({
    required this.id,
    required this.nombre,
    required this.apellidos,
    required this.fechaNacimiento,
    required this.avatar,
    required this.email,
    required this.telefono,
    required this.dni,
    required this.direccion,
    required this.rol,
    required this.observaciones,
    required this.medico,
  });
  late final String id;
  late final String nombre;
  late final String apellidos;
  late final String fechaNacimiento;
  late final String avatar;
  late final String email;
  late final String telefono;
  late final String dni;
  late final String direccion;
  late final String rol;
  late final String observaciones;
  late final Medico medico;

  Paciente.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    fechaNacimiento = json['fechaNacimiento'];
    avatar = json['avatar'];
    email = json['email'];
    telefono = json['telefono'];
    dni = json['dni'];
    direccion = json['direccion'];
    rol = json['rol'];
    observaciones = json['observaciones'];
    medico = Medico.fromJson(json['medico']);
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['fechaNacimiento'] = fechaNacimiento;
    _data['avatar'] = avatar;
    _data['email'] = email;
    _data['telefono'] = telefono;
    _data['dni'] = dni;
    _data['direccion'] = direccion;
    _data['rol'] = rol;
    _data['observaciones'] = observaciones;
    _data['medico'] = medico.toJson();
    return _data;
  }
}

class Medico {
  Medico({
    required this.id,
    required this.nombre,
    required this.apellidos,
    required this.fechaNacimiento,
    required this.avatar,
    required this.email,
    required this.telefono,
    required this.dni,
    required this.direccion,
    required this.rol,
    required this.numColegiado,
    required this.especialidad,
  });
  late final String id;
  late final String nombre;
  late final String apellidos;
  late final String fechaNacimiento;
  late final String avatar;
  late final String email;
  late final String telefono;
  late final String dni;
  late final String direccion;
  late final String rol;
  late final String numColegiado;
  late final Especialidad especialidad;

  Medico.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    fechaNacimiento = json['fechaNacimiento'];
    avatar = json['avatar'];
    email = json['email'];
    telefono = json['telefono'];
    dni = json['dni'];
    direccion = json['direccion'];
    rol = json['rol'];
    numColegiado = json['numColegiado'];
    especialidad = Especialidad.fromJson(json['especialidad']);
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['fechaNacimiento'] = fechaNacimiento;
    _data['avatar'] = avatar;
    _data['email'] = email;
    _data['telefono'] = telefono;
    _data['dni'] = dni;
    _data['direccion'] = direccion;
    _data['rol'] = rol;
    _data['numColegiado'] = numColegiado;
    _data['especialidad'] = especialidad.toJson();
    return _data;
  }
}

class Especialidad {
  Especialidad({
    required this.id,
    required this.nombre,
  });
  late final int id;
  late final String nombre;

  Especialidad.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    return _data;
  }
}

class Pageable {
  Pageable({
    required this.sort,
    required this.offset,
    required this.pageNumber,
    required this.pageSize,
    required this.paged,
    required this.unpaged,
  });
  late final Sort sort;
  late final int offset;
  late final int pageNumber;
  late final int pageSize;
  late final bool paged;
  late final bool unpaged;

  Pageable.fromJson(Map<String, dynamic> json) {
    sort = Sort.fromJson(json['sort']);
    offset = json['offset'];
    pageNumber = json['pageNumber'];
    pageSize = json['pageSize'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['sort'] = sort.toJson();
    _data['offset'] = offset;
    _data['pageNumber'] = pageNumber;
    _data['pageSize'] = pageSize;
    _data['paged'] = paged;
    _data['unpaged'] = unpaged;
    return _data;
  }
}

class Sort {
  Sort({
    required this.empty,
    required this.sorted,
    required this.unsorted,
  });
  late final bool empty;
  late final bool sorted;
  late final bool unsorted;

  Sort.fromJson(Map<String, dynamic> json) {
    empty = json['empty'];
    sorted = json['sorted'];
    unsorted = json['unsorted'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['empty'] = empty;
    _data['sorted'] = sorted;
    _data['unsorted'] = unsorted;
    return _data;
  }
}
