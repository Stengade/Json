package test;

import json.Json;

public class Test {

	public static void main(String[] args) {
		TestHelper[] helper = new TestHelper[] {
			new TestHelper(),
			new TestHelper(),
			new TestHelper()
		};
		System.out.println(Json.stringfy(helper));
	}

}