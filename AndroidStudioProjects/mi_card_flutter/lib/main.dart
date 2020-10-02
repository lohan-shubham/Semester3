import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.teal,
        body: SafeArea(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              CircleAvatar(
                radius: 50,
                // backgroundColor: Colors.red,
                backgroundImage: AssetImage('images/shubham.jpg'),
              ),
              Text(
                'Shubham Lohan',
                style: TextStyle(
                    fontSize: 40,
                    color: Colors.white,
                    fontWeight: FontWeight.bold,
                    fontFamily: 'Pacifico'),
              ),
              Text(
                'FLUTTER DEVELOPER',
                style: TextStyle(
                  fontSize: 20,
                  color: Colors.teal[100],
                  letterSpacing: 2.3,
                  fontFamily: 'Source Sans Pro',
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(
                height: 20,
                width: 150,
                child: Divider(
                  color: Colors.teal[100],
                ),
              ),
              Card(
                // padding: EdgeInsets.all(10),
                margin: EdgeInsets.symmetric(vertical: 10, horizontal: 25),
                child: ListTile(
                  leading: Icon(
                    Icons.phone,
                    color: Colors.teal,
                  ),
                  title: Text(
                    "+919313330008",
                    style: TextStyle(
                        color: Colors.teal[900],
                        fontSize: 18,
                        fontFamily: 'Source Sans Pro'),
                  ),
                ),
              ),
              Card(
                // padding: EdgeInsets.all(10),
                margin: EdgeInsets.symmetric(vertical: 10, horizontal: 25),
                child: ListTile(
                  leading: Icon(
                    Icons.email,
                    color: Colors.teal,
                  ),
                  title: Text(
                    "shubhamlohan99@gmail.com",
                    style: TextStyle(
                        color: Colors.teal[900],
                        fontSize: 18,
                        fontFamily: 'Source Sans Pro'),
                  ),
                ),
                ),
              Card(
                margin: EdgeInsets.symmetric(vertical: 10, horizontal: 25),
                child: ListTile(
                  leading: Icon(
                    Icons.alternate_email,
                    color: Colors.teal,
                  ),
                  title: Text(
                    'shubham19275@iiitd.ac.in',
                    style: TextStyle(
                      color: Colors.teal[900],
                      fontSize: 18,
                      fontFamily: 'Source Sans Pro',
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
