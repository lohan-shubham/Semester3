import 'package:flutter/material.dart';
import 'package:audioplayers/audio_cache.dart';

// void main() => runApp(XylophoneApp());
void main() {
  runApp(
    XylophoneApp(),
  );
}

class XylophoneApp extends StatelessWidget {
  void playSound(int node) {
    // final player = AudioCache();
    AudioCache player = new AudioCache();
    player.play('note$node.wav');
  }

  Expanded buildKey(Color node_color, int node) {
    return Expanded(
      child: FlatButton(
        color: node_color,
        onPressed: () {
          playSound(node);
        },
        // child: Text('Click Me'),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.black,
        body: SafeArea(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              buildKey(Colors.purple, 1),
              buildKey(Colors.indigo, 2),
              buildKey(Colors.blue, 3),
              buildKey(Colors.green, 4),
              buildKey(Colors.yellow, 5),
              buildKey(Colors.orange, 6),
              buildKey(Colors.red, 7),
            ],
          ),
        ),
      ),
    );
  }
}
