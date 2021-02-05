package com.ocrown;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class StringOperator {

    static String teststring = "{\"msgid\":\"13497664697342789008_1611825858\",\"action\":\"send\",\"from\":\"78ccedc336ad48317c1b16b1857d41e1\",\"tolist\":[\"TianLu\",\"YangGuang_1\",\"YangGuang\",\"XuShiWei\",\"XieDaoPeng\",\"HuangFei\",\"MuQingBing\",\"ShiQiLin\"],\"roomid\":\"wrcOiVDAAAL63cU1y39oxfFZ7Ztbc5fw\",\"msgtime\":1611825858093,\"msgtype\":\"text\",\"text\":{\"content\":\"中空铝条用的17.5的，明明单子上是15的！\"}}";

    static public void main(String[] args) {
        System.out.println(teststring);
        Vector<String> keys = new Vector<>();
        keys.add("msgid");
        keys.add("action");
        keys.add("from");
        keys.add("tolist");
        keys.add("roomid");
        keys.add("msgtime");
        keys.add("msgtype");
        keys.add("text");
        Map<String, String> ret = objectFromString(teststring, keys);
        System.out.println("msgid : " + ret.get("msgid"));
        System.out.println("action : " + ret.get("action"));
        System.out.println("from : " + ret.get("from"));
        System.out.println("tolist : " + ret.get("tolist"));
        System.out.println("roomid : " + ret.get("roomid"));
        System.out.println("msgtime : " + ret.get("msgtime"));
        System.out.println("msgtype : " + ret.get("msgtype"));
        System.out.println("text: " + ret.get("text"));
        Vector<String> tos = new Vector<>();
        tos = listFromString(ret.get("tolist"));
        String esc = "\\\\//''\"\"%%__\n\n\r\r";
        System.out.println(StringOperator.sqlCharEscape(esc));
    }

    static public Map<String, String> objectFromString(String object, Vector<String> keys) {
        return objectFromString(object);
    }

    static public Map<String, String> objectFromStringObsolete(String object, Vector<String> keys) {
        Map<String, String> retmap = new HashMap<>();
        for (Iterator<String> i = keys.iterator(); i.hasNext();) {
            String key = i.next();
            int indexkey = object.indexOf(key);
            int indexcolon = object.indexOf(":", indexkey);
            if (indexkey == -1) {
                System.err.println("key not found : " + key);
                continue;
            }
            if (indexkey + key.length() + 1 != indexcolon) {
                indexkey = object.indexOf(key, indexkey + 1);
                if (indexkey == -1) {
                    System.err.println("key not found : " + key);
                    continue;
                }
            }
            int indexcomma = object.indexOf(",", indexkey);
            int indexlbrace = object.indexOf("{", indexkey);
            int indexlbracket = object.indexOf("[", indexkey);
            int indexrbrace = object.indexOf("}", indexkey);
            if ((indexlbrace < indexcomma && indexlbrace != -1) || (indexlbracket < indexcomma && indexlbracket != -1)
                    || (indexcomma == -1 && (indexlbrace != -1 || indexlbracket != -1))) {
                // 如果目标是数组或对象
                int lbrace = 0;
                int lbracket = 0;
                int conflag = 0;
                int index = indexkey;
                int indexrbracket = object.indexOf("]", indexkey);
                do {
                    switch (getPositiveLowest(indexlbrace, indexlbracket, indexrbrace, indexrbracket)) {
                        case 0:
                            lbrace++;
                            index = indexlbrace + 1;
                            indexlbrace = object.indexOf("{", index);
                            break;
                        case 1:
                            lbracket++;
                            index = indexlbracket + 1;
                            indexlbracket = object.indexOf("[", index);
                            break;
                        case 2:
                            lbrace--;
                            index = indexrbrace + 1;
                            indexrbrace = object.indexOf("}", index);
                            break;
                        case 3:
                            lbracket--;
                            index = indexrbracket + 1;
                            indexrbracket = object.indexOf("]", index);
                            break;
                        case 4:
                            System.err.println("bad object string key :" + key);
                            conflag = 1;
                            break;
                    }
                    if (conflag == 1)
                        break;
                } while (lbrace != 0 || lbracket != 0);
                if (conflag == 1)
                    continue;
                indexcomma = object.indexOf(",", index);
                indexrbrace = object.indexOf("}", index);
            }
            // 如果对象是简单数据类型
            if (indexcomma == -1) {
                retmap.put(key, object.substring(indexcolon + 1, indexrbrace));
            } else {
                retmap.put(key, object.substring(indexcolon + 1, indexcomma));
            }

        }
        return retmap;
    }

    static int getPositiveLowest(int a, int b, int c, int d) {
        // 找寻最小正数
        Vector<Pair<Integer, Integer>> list = new Vector<>();
        list.add(new Pair<Integer, Integer>(0, a));
        list.add(new Pair<Integer, Integer>(1, b));
        list.add(new Pair<Integer, Integer>(2, c));
        list.add(new Pair<Integer, Integer>(3, d));
        list.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getLate().compareTo(o2.getLate());
            }
        });
        int i = 0;
        while (i < 4) {
            if (list.get(i).getLate() > 0)
                return list.get(i).getForm();
            i++;
        }
        return 4;
    }

    static public Vector<String> listFromString(String list) {
        Vector<String> retlist = new Vector<>();
        int exit = 0;
        int index = 1;
        int headindex = 1;
        do {
            int indexcomma = list.indexOf(",", index);
            int indexlbrace = list.indexOf("{", index);
            int indexlbracket = list.indexOf("[", index);
            int indexrbrace = list.indexOf("}", index);
            int indexrbracket = list.indexOf("]", index);
            if ((indexlbrace < indexcomma && indexlbrace != -1) || (indexlbracket < indexcomma && indexlbracket != -1)
                    || (indexcomma == -1 && (indexlbrace != -1 || indexlbracket != -1))) {
                int lbrace = 0;
                int lbracket = 0;
                int conflag = 0;
                // int index = indexkey;
                do {
                    switch (getPositiveLowest(indexlbrace, indexlbracket, indexrbrace, indexrbracket)) {
                        case 0:
                            lbrace++;
                            index = indexlbrace + 1;
                            indexlbrace = list.indexOf("{", index);
                            break;
                        case 1:
                            lbracket++;
                            index = indexlbracket + 1;
                            indexlbracket = list.indexOf("[", index);
                            break;
                        case 2:
                            lbrace--;
                            index = indexrbrace + 1;
                            indexrbrace = list.indexOf("}", index);
                            break;
                        case 3:
                            lbracket--;
                            index = indexrbracket + 1;
                            indexrbracket = list.indexOf("]", index);
                            break;
                        case 4:
                            System.err.println("bad list string");
                            conflag = 1;
                            break;
                    }
                    if (conflag == 1)
                        break;
                } while (lbrace != 0 || lbracket != 0);
                if (conflag == 1)
                    return retlist;
                indexcomma = list.indexOf(",", index);
                indexrbracket = list.indexOf("]", index);
            }
            // 如果对象是简单数据类型
            if (indexcomma == -1) {
                String elem = list.substring(headindex, indexrbracket);
                if (!elem.isEmpty())
                    retlist.add(elem);
                exit = 1;
            } else {
                retlist.add(list.substring(headindex, indexcomma));
                headindex = index = indexcomma + 1;
            }
        } while (exit == 0);
        return retlist;
    }

    static public Map<String, String> objectFromString(String object) {
        Map<String, String> retmap = new HashMap<>();
        int exit = 0;
        int index = 1;
        int headindex = 1;
        do {
            int indexcomma = object.indexOf(",", index);
            int indexlbrace = object.indexOf("{", index);
            int indexlbracket = object.indexOf("[", index);
            int indexrbrace = object.indexOf("}", index);
            int indexrbracket = object.indexOf("]", index);
            if ((indexlbrace < indexcomma && indexlbrace != -1) || (indexlbracket < indexcomma && indexlbracket != -1)
                    || (indexcomma == -1 && (indexlbrace != -1 || indexlbracket != -1))) {
                int lbrace = 0;
                int lbracket = 0;
                int conflag = 0;
                // int index = indexkey;
                do {
                    switch (getPositiveLowest(indexlbrace, indexlbracket, indexrbrace, indexrbracket)) {
                        case 0:
                            lbrace++;
                            index = indexlbrace + 1;
                            indexlbrace = object.indexOf("{", index);
                            break;
                        case 1:
                            lbracket++;
                            index = indexlbracket + 1;
                            indexlbracket = object.indexOf("[", index);
                            break;
                        case 2:
                            lbrace--;
                            index = indexrbrace + 1;
                            indexrbrace = object.indexOf("}", index);
                            break;
                        case 3:
                            lbracket--;
                            index = indexrbracket + 1;
                            indexrbracket = object.indexOf("]", index);
                            break;
                        case 4:
                            System.err.println("bad object string");
                            conflag = 1;
                            break;
                    }
                    if (conflag == 1)
                        break;
                } while (lbrace != 0 || lbracket != 0);
                if (conflag == 1)
                    return retmap;
                indexcomma = object.indexOf(",", index);
                indexrbrace = object.indexOf("}", index);
            }
            String elem;
            if (indexcomma == -1) {
                elem = object.substring(headindex, indexrbrace);
                exit = 1;

            } else {
                elem = object.substring(headindex, indexcomma);
                headindex=index=indexcomma+1;
            }
            if (!elem.isEmpty()) {
                int elemindexcolon = elem.indexOf(":");
                retmap.put(elem.substring(1, elemindexcolon - 1), elem.substring(elemindexcolon + 1));
            }
        } while (exit == 0);
        return retmap;
    }

    public static String underline2Hump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String underline2Bighump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            result.append(s.substring(0, 1).toUpperCase());
            result.append(s.substring(1).toLowerCase());
        }
        return result.toString();
    }

    public static String value2String(String value) {
        return value.substring(1, value.length() - 1);
    }

    public static Vector<String> list2StringList(Vector<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            list.set(i, s.substring(1, s.length() - 1));
        }
        return list;
    }

    public static String sqlCharEscape(String str) {
        /*
         * int slindex=str.indexOf("/"); while(slindex!=-1){ str=str.substring(0,
         * slindex)+"/"+str.substring(slindex); slindex=str.indexOf("/",slindex+2); }
         */
        int bslindex = str.indexOf("\\");
        while (bslindex != -1) {
            str = str.substring(0, bslindex) + "\\" + str.substring(bslindex);
            bslindex = str.indexOf("\\", bslindex + 2);
        }
        int sqmindex = str.indexOf("'");
        while (sqmindex != -1) {
            str = str.substring(0, sqmindex) + "\\" + str.substring(sqmindex);
            sqmindex = str.indexOf("'", sqmindex + 2);
        }
        int dqmindex = str.indexOf("\"");
        while (dqmindex != -1) {
            str = str.substring(0, dqmindex) + "\\" + str.substring(dqmindex);
            dqmindex = str.indexOf("\"", dqmindex + 2);
        }
        int crindex = str.indexOf("\r");
        while (crindex != -1) {
            str = str.substring(0, crindex) + "\\r" + str.substring(crindex + 1);
            crindex = str.indexOf("\r", crindex + 2);
        }
        int lbindex = str.indexOf("\n");
        while (lbindex != -1) {
            str = str.substring(0, lbindex) + "\\n" + str.substring(lbindex + 1);
            lbindex = str.indexOf("\n", lbindex + 2);
        }
        int psindex = str.indexOf("%");
        while (psindex != -1) {
            str = str.substring(0, psindex) + "\\" + str.substring(psindex);
            psindex = str.indexOf("%", psindex + 2);
        }
        /*
         * int glindex=str.indexOf("_"); while(glindex!=-1){ str=str.substring(0,
         * glindex)+"/"+str.substring(glindex); glindex=str.indexOf("_",glindex+2); }
         */
        return str;
    }

    public static String list2String(Vector<?>list){
        String retlist="[";
        for(Iterator<?> i=list.iterator();i.hasNext();){
            retlist=retlist+i.next().toString();
            if(i.hasNext()){
                retlist=retlist+",";
            }else{
                retlist=retlist+"]";
            }
        }
        return retlist;
    }
}
