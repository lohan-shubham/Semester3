import 'package:flutter/material.dart';
import 'dart:math';

void main() {
  return runApp(
    MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.red,
        appBar: AppBar(
          title: Text('Dicee'),
          backgroundColor: Colors.red,
        ),
        body: DicePage(),
      ),
    ),
  );
}

// class DicePage extends StatelessWidget {
//   int leftDiceNumber = 5;
//   @override
//   Widget build(BuildContext context) {
//     leftDiceNumber = 1;
//     return Center(
//       child: Row(
//         children: <Widget>[
//           Expanded(
//             // flex: 2,
//             child: FlatButton(
//               onPressed: () {
//                 print('Left Button is pressed.');
//               },
//               child: Image.asset('images/dice$leftDiceNumber.png'),
//             ),
//           ),
//           // SizedBox(
//           //   width: 10,
//           //   // height: 10,
//           // ),
//           Expanded(
//             child: FlatButton(
//               onPressed: () {
//                 print('Right Button is pressed.');
//               },
//               child: Image.asset('images/dice4.png'),
//             ),
//           ),
//         ],
//       ),
//     );
//   }
// }

class DicePage extends StatefulWidget {
  @override
  _DicePageState createState() => _DicePageState();
}

class _DicePageState extends State<DicePage> {
  int leftDiceNumber = 1;
  int rightDiceNumber = 2;
  void changedDiceFace() {
    leftDiceNumber = 1 + Random().nextInt(6);
    rightDiceNumber = 1 + Random().nextInt(6);
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Row(
        children: <Widget>[
          Expanded(
            // flex: 2,
            child: FlatButton(
              onPressed: () {
                print('Left Button is pressed.');
                setState(() {
                  changedDiceFace();
                });
              },
              child: Image.asset('images/dice$leftDiceNumber.png'),
            ),
          ),
          // SizedBox(
          //   width: 10,
          //   // height: 10,
          // ),
          Expanded(
            child: FlatButton(
              onPressed: () {
                setState(() {
                  changedDiceFace();
                });
                print('Right Button is pressed.');
              },
              child: Image.asset('images/dice$rightDiceNumber.png'),
            ),
          ),
        ],
      ),
    );
  }
}
