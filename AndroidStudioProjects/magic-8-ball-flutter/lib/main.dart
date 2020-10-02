import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() => runApp(
      MaterialApp(
        home: MagicBall(),
      ),
    );

class MagicBall extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blue,
      appBar: AppBar(
        backgroundColor: Colors.blue[900],
        title: Text('Ask Me Anything'),
      ),
      body: showBall(),
    );
  }
}

class showBall extends StatefulWidget {
  @override
  _showBallState createState() => _showBallState();
}

class _showBallState extends State<showBall> {
  int result = 1;
  @override
  Widget build(BuildContext context) {
    return Center(
      child: FlatButton(
        onPressed: () {
          setState(() {
            result = 1 + Random().nextInt(5);
          });
        },
        child: Image.asset('images/ball$result.png'),
      ),
    );
  }
}
