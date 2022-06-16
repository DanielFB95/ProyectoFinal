import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/screens/profile_screen.dart';
import 'package:flutter_proyecto_final/screens/receta_screen.dart';
import 'package:flutter_proyecto_final/screens/map_page.dart';

class MenuPacienteScreen extends StatefulWidget {
  const MenuPacienteScreen({Key? key}) : super(key: key);

  @override
  _MenuPacienteScreenState createState() => _MenuPacienteScreenState();
}

class _MenuPacienteScreenState extends State<MenuPacienteScreen> {
  int _currentIndex = 0;

  List<Widget> pages = [
    const RecetaScreen(),
    const ProfileScreen(),
    const MapClickPage()
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: pages[_currentIndex], bottomNavigationBar: _buildBottomBar());
  }

  Widget _buildBottomBar() {
    return Container(
        decoration: const BoxDecoration(
            border: Border(
          top: BorderSide(
            color: Color(0xfff1f1f1),
            width: 1.0,
          ),
        )),
        padding: const EdgeInsets.symmetric(horizontal: 20.0),
        height: 70,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            GestureDetector(
              child: Icon(Icons.home,
                  color: _currentIndex == 0
                      ? Colors.black
                      : const Color(0xff999999)),
              onTap: () {
                setState(() {
                  _currentIndex = 0;
                });
              },
            ),
            GestureDetector(
              onTap: () {
                setState(() {
                  _currentIndex = 1;
                });
              },
              child: Container(
                padding: const EdgeInsets.all(5),
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(100),
                    border: Border.all(
                        color: _currentIndex == 1
                            ? Colors.black
                            : Colors.transparent,
                        width: 1)),
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(100),
                  child: Image.asset(
                    'assets/images/iconAdmin.png',
                    width: 30,
                    height: 30,
                  ),
                ),
              ),
            ),
            GestureDetector(
              child: Icon(Icons.place,
                  color: _currentIndex == 2
                      ? Colors.black
                      : const Color(0xff999999)),
              onTap: () {
                setState(() {
                  _currentIndex = 2;
                });
              },
            ),
          ],
        ));
  }
}
