// ignore_for_file: unused_field

import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/screens/google_map_app.dart';
import 'package:flutter_proyecto_final/utils/constant.dart';
import 'package:flutter_proyecto_final/utils/preferences.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

const CameraPosition _kInitialPosition =
    CameraPosition(target: LatLng(33, -5), zoom: 5.0);

class MapClickPage extends GoogleMapAppPage {
  const MapClickPage() : super(const Icon(Icons.mouse), 'Map click');

  @override
  Widget build(BuildContext context) {
    return const _MapClickBody();
  }
}

class _MapClickBody extends StatefulWidget {
  const _MapClickBody();

  @override
  State<StatefulWidget> createState() => _MapClickBodyState();
}

class _MapClickBodyState extends State<_MapClickBody> {
  _MapClickBodyState();

  GoogleMapController? mapController;
  LatLng? _lastTap;
  LatLng? _lastLongPress;

  List<String> latLong = [];
  String lat = '';
  String long = '';

  @override
  void initState() {
    PreferenceUtils.init();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final GoogleMap googleMap = GoogleMap(
      onMapCreated: onMapCreated,
      initialCameraPosition: _kInitialPosition,
      onTap: (LatLng pos) {
        setState(() {
          _lastTap = pos;

          PreferenceUtils.setDouble(Constant.LAT, pos.latitude);
          PreferenceUtils.setDouble(Constant.LONG, pos.longitude);

          Navigator.pushNamed(context, '/');
        });
      },
      onLongPress: (LatLng pos) {
        setState(() {
          _lastLongPress = pos;
        });
      },
    );

    final List<Widget> columnChildren = <Widget>[
      SingleChildScrollView(
        child: Center(
          child: SizedBox(
            width: MediaQuery.of(context).size.width,
            height: MediaQuery.of(context).size.height / 1.113,
            child: googleMap,
          ),
        ),
      ),
    ];

    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: columnChildren,
    );
  }

  void onMapCreated(GoogleMapController controller) async {
    setState(() {
      mapController = controller;
    });
  }
}
