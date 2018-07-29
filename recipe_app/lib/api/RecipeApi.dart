import 'dart:async';
import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:recipe_app/model/CookingMethod.dart';
import 'package:recipe_app/model/Ingredients.dart';
import 'package:recipe_app/model/RecipeModel.dart';

class RecipeApi {
  // 環境によって変更する
  // iOSのパス
//  static const String _api_path = "http://127.0.0.1:3000/recipe";
  // androidのパス
  static const String _api_path = "http://10.0.2.2:3000/recipe";

  var _recipeMap = new Map<String, RecipeModel>();

  Future<Map<String, RecipeModel>> getRecipe() async {
    await _request();
    return _recipeMap;
  }

  _request() async {
    var response = await http.get(_api_path);
    var data = json.decode(response.body);

    // recipe
    for (var item in data) {
      _recipeMap[item['recipe_id']] = RecipeModel.fromJson(item);
    }
  }
}
