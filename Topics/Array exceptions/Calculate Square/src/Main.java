class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {

        if (array == null || index < 0 || index > array.length - 1) {
            System.out.println("Exception!");
        } else  {
            System.out.println(array[index] * array[index]);
        }

        /*try {
            System.out.println(Math.pow(array[index], 2));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (NegativeArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }*/

    }
}