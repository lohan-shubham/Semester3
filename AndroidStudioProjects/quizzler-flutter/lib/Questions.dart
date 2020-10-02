import 'package:flutter/cupertino.dart';

class Question {
  String questionText;
  bool questionAnswer;

  Question(String q, bool a) {
    questionText = q;
    questionAnswer = a;
  }
}
