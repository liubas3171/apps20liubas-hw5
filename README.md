# apps20liubas-hw5

Завдання:
Реалізувати наступні методи класу AsIntStream по роботі з потоком (stream) цілочисельних данgiих:

static IntStream of(int... values)
створює початковий потік на основі масиву цілих чисел


- Double average()
середнє значення чисел в потоці. Термінальній метод. IllegalArgumentException - if empty
- Integer max()/min()
максимальне / мінімальне значення числа в потоці. Термінальній метод. IllegalArgumentException - if empty
- long count()
кількість значень (елементів) в потоці. Термінальній метод.
- Integer sum()
сума всіх значень в потоці. Термінальній метод. IllegalArgumentException - if empty
- int[] toArray()
повертає потік у вигляді масиву. Термінальній метод.
- void forEach(IntConsumer action)
для кожного значення з потоку виконує операцію зазначену в реалізації IntConsumer. Даний метод є термінальним і нічого не повертає
- IntStream filter(IntPredicate predicate)
для кожного значення з потоку перевіряє його на предмет чи задовольняє воно умові в реалізації IntPredicate, якщо так - повертає його в результуючий потік, якщо ні - викидає
- IntStream map(IntUnaryOperator mapper)
застосовує до кожного зі значень потоку реалізацію IntUnaryOperator і повертає його в результуючий потік
- IntStream flatMap(IntToIntStreamFunction func)
застосовує до кожного зі значень потоку реалізацію IntToIntStreamFunction, яка на основі кожного зі значення створює новий потік значень, які потім об'єднуються в один результуючий потік (см. http://java.amitph.com/2014/02/java-8-streams-api-intermediate.html)
- int reduce(int identity, IntBinaryOperator op)
виконує згортку значень потоку в ціле число, початкове значення задається identity, функція згортки - в реалізації IntBinaryOperator. Термінальній метод.


Шаблон Maven-проекту (не мод.ифікуйте pom.xml та checkstyle.xml): Streams.zip

В даному проекті вже є шаблони класів і невеликий тест, які повинен почати проходити після реалізації частини методів.

При реалізації можна використовувати стандартні колекції з Java, але не можливості Java Stream API.
При реалізації спробуйте реалізувати функціональність проміжних методів (filter, map, flatMap)  таким чином, щоб визначені у них операції по виконанню дій над значеннями потоку не виконувались до виклику одного з термінальних методів. Це буде відповідати підходу “лінивих обчислень”. Таку реалізацію можна зробити використовуючи Iterator pattern з попереднього завдання. Також такий підхід дозволить не створювати зайвих проміжних масивів/колекцій.
Необхідно написати модульні тести на всі методи класу AsIntStream
