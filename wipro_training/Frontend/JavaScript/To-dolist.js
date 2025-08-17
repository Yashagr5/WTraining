let timer;
        let minutes = 0;
        let seconds = 0;

        function startTimer() {
            timer = setInterval(function() {
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    minutes++;
                }
                document.getElementById('timer').textContent = 
                    (minutes < 10 ? '0' : '') + minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
            }, 1000);
        }

        startTimer(); // Start timer when the page loads

        // Todo List functionality using closure
        function createTodoList() {
            let tasks = [];

            function addTask(task) {
                tasks.push(task);
                displayTasks();
            }

            function removeTask(index) {
                tasks.splice(index, 1);
                displayTasks();
            }

            function displayTasks() {
                const todoListElement = document.getElementById('todo-list');
                todoListElement.innerHTML = ''; 
                tasks.forEach((task, index) => {
                    const taskElement = document.createElement('li');
                    taskElement.className = 'todo-item';
                    taskElement.innerHTML = `${task} <button onclick="removeTask(${index})">Remove</button>`;
                    todoListElement.appendChild(taskElement);
                });
            }

            return {
                addTask,
                removeTask,
                displayTasks
            };
        }

        const todoList = createTodoList();

        function addTask() {
            const taskInput = document.getElementById('taskInput');
            if (taskInput.value.trim()) {
                todoList.addTask(taskInput.value.trim());
                taskInput.value = ''; // Clear input after adding
            }
        }

        function removeTask(index) {
            todoList.removeTask(index);
        }

        // Calculator functionality
        function calculate(operation) {
            const num1 = parseFloat(document.getElementById('num1').value);
            const num2 = parseFloat(document.getElementById('num2').value);
            let result;

            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                default:
                    result = "Invalid operation";
            }

            document.getElementById('calc-result').textContent = "Result: " + result;
        }
  