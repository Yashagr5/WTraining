document.getElementById('MarksForm').addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent form submission
    
    let subjectInput = document.getElementById('SubjectInput').value;
    
    // Split the input by commas and then process each subject
    let subjects = subjectInput.split(',').map(item => item.trim());
    
    let totalMarks = 0;
    let subjectDetails = [];

    subjects.forEach(subject => {
        let parts = subject.split('(');
        let subjectName = parts[0].trim();
        let marks = parseInt(parts[1].replace('Marks)', '').trim());

        // Add marks to total
        totalMarks += marks;

        // Calculate grade based on marks
        let grade = calculateGrade(marks);

        subjectDetails.push({ name: subjectName, marks: marks, grade: grade });
    });

    // Display the result in a formatted way
    let reportHTML = "<h3>Report Card</h3>";
    subjectDetails.forEach(detail => {
        reportHTML += `<p>${detail.name}: ${detail.marks} marks - Grade: ${detail.grade}</p>`;
    });

    let average = totalMarks / subjectDetails.length;
    let overallGrade = calculateGrade(average);
    reportHTML += `<p><strong>Average Marks: ${average.toFixed(2)}</strong></p>`;
    reportHTML += `<p><strong>Overall Grade: ${overallGrade}</strong></p>`;

    document.body.innerHTML += reportHTML;
});

// Function to calculate grade based on marks
function calculateGrade(marks) {
    if (marks >= 90) {
        return 'A';
    } else if (marks >= 75) {
        return 'B';
    } else if (marks >= 50) {
        return 'C';
    } else {
        return 'F';
    }
}
