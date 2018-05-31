function setCapital(obj){
	var value=$(obj).val();
	if(value>10000000000){
		value = "1000000000.00";
		$(obj).val(value);
	}
	part = String(value).split(".");
	newchar = "";
	//小数点前进行转化
	for (i = part[0].length - 1; i >= 0; i--) {
		tmpnewchar = ""
		perchar = part[0].charAt(i);
		switch (perchar) {
			case "0": tmpnewchar = "零" + tmpnewchar; break;
			case "1": tmpnewchar = "一" + tmpnewchar; break;
			case "2": tmpnewchar = "二" + tmpnewchar; break;
			case "3": tmpnewchar = "三" + tmpnewchar; break;
			case "4": tmpnewchar = "四" + tmpnewchar; break;
			case "5": tmpnewchar = "五" + tmpnewchar; break;
			case "6": tmpnewchar = "六" + tmpnewchar; break;
			case "7": tmpnewchar = "七" + tmpnewchar; break;
			case "8": tmpnewchar = "八" + tmpnewchar; break;
			case "9": tmpnewchar = "九" + tmpnewchar; break;
		}
		switch (part[0].length - i - 1) {
			case 0: tmpnewchar = tmpnewchar + "元"; break;
			case 1: if (perchar != 0) tmpnewchar = tmpnewchar + "十"; break;
			case 2: if (perchar != 0) tmpnewchar = tmpnewchar + "百"; break;
			case 3: if (perchar != 0) tmpnewchar = tmpnewchar + "千"; break;
			case 4: tmpnewchar = tmpnewchar + "万"; break;
			case 5: if (perchar != 0) tmpnewchar = tmpnewchar + "十"; break;
			case 6: if (perchar != 0) tmpnewchar = tmpnewchar + "百"; break;
			case 7: if (perchar != 0) tmpnewchar = tmpnewchar + "千"; break;
			case 8: tmpnewchar = tmpnewchar + "亿"; break;
			case 9: tmpnewchar = tmpnewchar + "十"; break;
		}
		newchar = tmpnewchar + newchar;
	}
	//小数点之后进行转化
	if (value.indexOf(".") != -1) {
		if (part[1].length > 2) {
			part[1] = part[1].substr(0, 2)
		}
		for (i = 0; i < part[1].length; i++) {
			tmpnewchar = ""
			perchar = part[1].charAt(i)
			switch (perchar) {
				case "0": tmpnewchar = "零" + tmpnewchar; break;
				case "1": tmpnewchar = "一" + tmpnewchar; break;
				case "2": tmpnewchar = "二" + tmpnewchar; break;
				case "3": tmpnewchar = "三" + tmpnewchar; break;
				case "4": tmpnewchar = "四" + tmpnewchar; break;
				case "5": tmpnewchar = "五" + tmpnewchar; break;
				case "6": tmpnewchar = "六" + tmpnewchar; break;
				case "7": tmpnewchar = "七" + tmpnewchar; break;
				case "8": tmpnewchar = "八" + tmpnewchar; break;
				case "9": tmpnewchar = "九" + tmpnewchar; break;
			}
			if (i == 0) tmpnewchar = tmpnewchar + "角";
			if (i == 1) tmpnewchar = tmpnewchar + "分";
			newchar = newchar + tmpnewchar;
		}
	}
	while (newchar.search("零零") != -1){
		newchar = newchar.replace("零零", "零");
		newchar = newchar.replace("零亿", "亿");
		newchar = newchar.replace("亿万", "亿");
		newchar = newchar.replace("零万", "万");
		newchar = newchar.replace("零元", "元");
		newchar = newchar.replace("零角", "");
		newchar = newchar.replace("零分", "");
	}
	if (newchar.charAt(newchar.length - 1) == "元"){
		newchar = newchar + "整";
	}
	newchar=newchar.replace("零元整", "元整");
	$(obj).parent("div").children(".capitalSpan").html(newchar);
}