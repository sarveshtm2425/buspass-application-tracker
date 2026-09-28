const API_URL = "/api";


// =====================================================
// SECTION NAVIGATION
// =====================================================

function showSection(sectionId) {

    document.getElementById("studentSection").style.display = "none";
    document.getElementById("applicationSection").style.display = "none";
    document.getElementById("adminSection").style.display = "none";

    document.getElementById(sectionId).style.display = "block";


    if (sectionId === "studentSection") {
        loadStudents();
    }


    if (sectionId === "applicationSection") {
        loadApplications();
    }


    if (sectionId === "adminSection") {
        showAdminSection("routeAdminSection");
        loadRoutes();
    }
}


// =====================================================
// ADMIN SECTION NAVIGATION
// =====================================================

function showAdminSection(sectionId) {

    document.getElementById("routeAdminSection").style.display = "none";

    document.getElementById("applicationAdminSection").style.display = "none";

    document.getElementById("expiringAdminSection").style.display = "none";


    document.getElementById(sectionId).style.display = "block";


    if (sectionId === "routeAdminSection") {
        loadRoutes();
    }


    if (sectionId === "applicationAdminSection") {
        loadAdminApplications();
    }


    if (sectionId === "expiringAdminSection") {
        loadExpiringApplications();
    }
}


// =====================================================
// STUDENT
// =====================================================

document.getElementById("studentForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();


        const student = {

            name: document.getElementById("studentName").value,

            email: document.getElementById("studentEmail").value,

            phone: document.getElementById("studentPhone").value
        };


        try {

            const response = await fetch(
                `${API_URL}/students`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(student)
                }
            );


            if (!response.ok) {

                const errorText = await response.text();

                throw new Error(
                    errorText || "Unable to register student."
                );
            }


            alert("Student registered successfully!");

            document.getElementById("studentForm").reset();

            loadStudents();

        } catch (error) {

            alert(
                "Student registration could not be completed.\n\n" +
                "Reason: " +
                error.message
            );
        }

    });


async function loadStudents() {

    try {

        const response =
            await fetch(`${API_URL}/students`);


        if (!response.ok) {

            throw new Error("Unable to load students.");
        }


        const students =
            await response.json();


        const studentList =
            document.getElementById("studentList");


        studentList.innerHTML = "";


        students.forEach(student => {

            studentList.innerHTML += `

                <div class="card">

                    <p>
                        <strong>ID:</strong>
                        ${student.id}
                    </p>

                    <p>
                        <strong>Name:</strong>
                        ${student.name}
                    </p>

                    <p>
                        <strong>Email:</strong>
                        ${student.email}
                    </p>

                    <p>
                        <strong>Phone:</strong>
                        ${student.phone}
                    </p>

                </div>

            `;
        });


    } catch (error) {

        console.error(error);
    }
}


// =====================================================
// STUDENT APPLICATION
// =====================================================

document.getElementById("applicationForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();


        const studentId =
            document.getElementById("applicationStudentId").value;


        const routeId =
            document.getElementById("applicationRouteId").value;


        const boardingPoint =
            document.getElementById("applicationBoardingPoint").value;


        const photoReference =
            document.getElementById("photoReference").value;


        const application = {

            student: {
                id: Number(studentId)
            },

            busRoute: {
                id: Number(routeId)
            },

            boardingPoint: boardingPoint,

            photoReference: photoReference,

            status: "PENDING"
        };


        try {

            const response =
                await fetch(
                    `${API_URL}/applications`,
                    {
                        method: "POST",

                        headers: {
                            "Content-Type": "application/json"
                        },

                        body: JSON.stringify(application)
                    }
                );


            if (!response.ok) {

                const errorText =
                    await response.text();

                throw new Error(
                    errorText ||
                    "Unable to submit application."
                );
            }


            alert("Application submitted successfully!");


            document
                .getElementById("applicationForm")
                .reset();


            loadApplications();


        } catch (error) {

            alert(
                "Application could not be submitted.\n\n" +
                "Reason: " +
                error.message
            );
        }

    });


async function loadApplications() {

    try {

        const response =
            await fetch(`${API_URL}/applications`);


        if (!response.ok) {

            throw new Error(
                "Unable to load applications."
            );
        }


        const applications =
            await response.json();


        const applicationList =
            document.getElementById(
                "applicationList"
            );


        applicationList.innerHTML = "";


        applications.forEach(application => {

            applicationList.innerHTML += `

                <div class="card">

                    <p>
                        <strong>Application ID:</strong>
                        ${application.id}
                    </p>

                    <p>
                        <strong>Student ID:</strong>
                        ${application.student.id}
                    </p>

                    <p>
                        <strong>Route:</strong>
                        ${application.busRoute.routeName}
                    </p>

                    <p>
                        <strong>Boarding Point:</strong>
                        ${application.boardingPoint}
                    </p>

                    <p>
                        <strong>Status:</strong>
                        ${application.status}
                    </p>

                    ${
                application.passNumber
                    ?
                    `<p>
                            <strong>Pass Number:</strong>
                            ${application.passNumber}
                        </p>`
                    : ""
            }

                    ${
                application.validFrom
                    ?
                    `<p>
                            <strong>Valid From:</strong>
                            ${application.validFrom}
                        </p>`
                    : ""
            }

                    ${
                application.validUntil
                    ?
                    `<p>
                            <strong>Valid Until:</strong>
                            ${application.validUntil}
                        </p>`
                    : ""
            }

                    ${
                application.rejectionReason
                    ?
                    `<p>
                            <strong>Rejection Reason:</strong>
                            ${application.rejectionReason}
                        </p>`
                    : ""
            }

                </div>

            `;
        });


    } catch (error) {

        console.error(error);
    }
}


// =====================================================
// ADMIN - BUS ROUTES
// =====================================================

document.getElementById("routeForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();


        const route = {

            routeName:
            document.getElementById("routeName").value,

            boardingPoint:
            document.getElementById("routeBoardingPoint").value,

            destination:
            document.getElementById("destination").value
        };


        try {

            const response =
                await fetch(
                    `${API_URL}/routes`,
                    {
                        method: "POST",

                        headers: {
                            "Content-Type": "application/json"
                        },

                        body: JSON.stringify(route)
                    }
                );


            if (!response.ok) {

                const errorText =
                    await response.text();

                throw new Error(
                    errorText ||
                    "Unable to add bus route."
                );
            }


            alert("Bus route added successfully!");


            document
                .getElementById("routeForm")
                .reset();


            loadRoutes();


        } catch (error) {

            alert(
                "Bus route could not be added.\n\n" +
                "Reason: " +
                error.message
            );
        }

    });


async function loadRoutes() {

    try {

        const response =
            await fetch(`${API_URL}/routes`);


        if (!response.ok) {

            throw new Error(
                "Unable to load bus routes."
            );
        }


        const routes =
            await response.json();


        const routeList =
            document.getElementById("routeList");


        routeList.innerHTML = "";


        routes.forEach(route => {

            routeList.innerHTML += `

                <div class="card">

                    <p>
                        <strong>Route ID:</strong>
                        ${route.id}
                    </p>

                    <p>
                        <strong>Route:</strong>
                        ${route.routeName}
                    </p>

                    <p>
                        <strong>Boarding Point:</strong>
                        ${route.boardingPoint}
                    </p>

                    <p>
                        <strong>Destination:</strong>
                        ${route.destination}
                    </p>

                </div>

            `;
        });


    } catch (error) {

        console.error(error);
    }
}


// =====================================================
// ADMIN - VIEW APPLICATIONS
// =====================================================

async function loadAdminApplications() {

    try {

        const response =
            await fetch(`${API_URL}/applications`);


        if (!response.ok) {

            throw new Error(
                "Unable to load applications."
            );
        }


        const applications =
            await response.json();


        const applicationList =
            document.getElementById(
                "adminApplicationList"
            );


        applicationList.innerHTML = "";


        applications.forEach(application => {

            applicationList.innerHTML += `

                <div class="card">

                    <p>
                        <strong>Application ID:</strong>
                        ${application.id}
                    </p>

                    <p>
                        <strong>Student ID:</strong>
                        ${application.student.id}
                    </p>

                    <p>
                        <strong>Route:</strong>
                        ${application.busRoute.routeName}
                    </p>

                    <p>
                        <strong>Boarding Point:</strong>
                        ${application.boardingPoint}
                    </p>

                    <p>
                        <strong>Photo Reference:</strong>
                        ${application.photoReference}
                    </p>

                    <p>
                        <strong>Status:</strong>
                        ${application.status}
                    </p>

                    ${
                application.passNumber
                    ?
                    `<p>
                            <strong>Pass Number:</strong>
                            ${application.passNumber}
                        </p>`
                    : ""
            }

                    ${
                application.validFrom
                    ?
                    `<p>
                            <strong>Valid From:</strong>
                            ${application.validFrom}
                        </p>`
                    : ""
            }

                    ${
                application.validUntil
                    ?
                    `<p>
                            <strong>Valid Until:</strong>
                            ${application.validUntil}
                        </p>`
                    : ""
            }

                    ${
                application.rejectionReason
                    ?
                    `<p>
                            <strong>Rejection Reason:</strong>
                            ${application.rejectionReason}
                        </p>`
                    : ""
            }


                    ${
                application.status === "PENDING"
                    ?

                    `
                        <button
                            onclick="approveApplication(${application.id})">
                            Approve
                        </button>

                        <button
                            onclick="rejectApplication(${application.id})">
                            Reject
                        </button>
                        `

                    : ""
            }

                </div>

            `;
        });


    } catch (error) {

        console.error(error);
    }
}


// =====================================================
// ADMIN - APPROVE
// =====================================================

async function approveApplication(id) {

    try {

        const response =
            await fetch(
                `${API_URL}/applications/${id}/approve`,
                {
                    method: "PUT"
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            throw new Error(
                errorText ||
                "Unable to approve application."
            );
        }


        alert(
            "Application approved successfully!"
        );


        loadAdminApplications();


    } catch (error) {

        alert(
            "Application could not be approved.\n\n" +
            "Reason: " +
            error.message
        );
    }
}


// =====================================================
// ADMIN - REJECT
// =====================================================

async function rejectApplication(id) {

    const reason =
        prompt("Enter rejection reason:");


    if (!reason || reason.trim() === "") {

        alert(
            "Application could not be rejected.\n\n" +
            "Reason: Rejection reason is required."
        );

        return;
    }


    try {

        const response =
            await fetch(
                `${API_URL}/applications/${id}/reject?reason=${encodeURIComponent(reason)}`,
                {
                    method: "PUT"
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            throw new Error(
                errorText ||
                "Unable to reject application."
            );
        }


        alert(
            "Application rejected successfully!"
        );


        loadAdminApplications();


    } catch (error) {

        alert(
            "Application could not be rejected.\n\n" +
            "Reason: " +
            error.message
        );
    }
}


// =====================================================
// ADMIN - EXPIRING PASSES
// =====================================================

async function loadExpiringApplications() {

    try {

        const response =
            await fetch(
                `${API_URL}/applications/expiring`
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            throw new Error(
                errorText ||
                "Unable to load expiring passes."
            );
        }


        const applications =
            await response.json();


        const expiringList =
            document.getElementById(
                "expiringList"
            );


        expiringList.innerHTML = "";


        if (applications.length === 0) {

            expiringList.innerHTML =
                "<p>No passes are expiring within 30 days.</p>";

            return;
        }


        applications.forEach(application => {

            expiringList.innerHTML += `

                <div class="card">

                    <p>
                        <strong>Application ID:</strong>
                        ${application.id}
                    </p>

                    <p>
                        <strong>Student ID:</strong>
                        ${application.student.id}
                    </p>

                    <p>
                        <strong>Pass Number:</strong>
                        ${application.passNumber}
                    </p>

                    <p>
                        <strong>Valid Until:</strong>
                        ${application.validUntil}
                    </p>

                </div>

            `;
        });


    } catch (error) {

        alert(
            "Expiring passes could not be loaded.\n\n" +
            "Reason: " +
            error.message
        );
    }
}