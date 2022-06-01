class func {
    //bai 1
    public static int tongdayso(int[] arr) {
        int summ = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            summ += arr[i];
        }
        return summ;
    }

    // bai 2
        public char[] maxChar(String a) {
            int temp = 0;
            int dem = 0;
            int b = 0;
            char[] maxchar = new char[a.length()];
            for (int i = 0; i < a.length(); i++)
            {
                char a_1 = a.charAt(i);
                for (int j = i + 1; j < a.length(); j++)
                {
                    char a_2 = a.charAt(j);
                    if (a_1 != a_2)
                    {
                        dem++;
                    }
                }
                if (dem > temp)
                {
                    temp = dem;
                    maxchar[b] = a_1;
                    b++;
                }
            }
            return maxchar;
        }
    // bai 3
    public static int[] sapxep(int[] arr) {
        int a = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    a = arr[j];
                    arr[j] = arr[i];
                    arr[i] = a;
                }
            }
        }
        return arr;
    }

    // bai 4
    public static boolean nguyento(int n) {
        if (n < 2) {
            return false;
        }
        int can = (int) Math.sqrt(n);
        for (int i = 2; i <= can; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    //bai 5
    public static Exception Exceptiontriagle() {
        return new Exception("khong phai la tam giac");
    }
    public static void tamgiac(double a, double b, double c) throws Exception {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw Exceptiontriagle();
        } else {
            double p = (a+b+c)/2;
            double s = p*(p-a)*(p-b)*(p-c);
            System.out.println("dien tich tam giac la :" + s);
        }
    }
    //bai 6
    public static void inhinh(int bk) {
        double distance;
        for (int row = 0; row <= 2 * bk; row++) {
            for (int col = 0; col <= 2 * bk; col++) {
                distance = Math.sqrt((row - bk) * (row - bk) + (col - bk) * (col - bk));
                if (distance > bk - 0.5 && distance < bk + 0.5)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
