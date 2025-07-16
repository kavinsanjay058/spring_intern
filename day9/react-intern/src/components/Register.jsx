import { useState } from "react";
import axios from "axios";

const Register = () => {
  const [name, setName] = useState("");
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [roleNames, setRoleNames] = useState(""); // comma-separated (e.g., "ADMIN,USER")

  async function handleRegister(event) {
    event.preventDefault();
    try {
      const rolesArray = roleNames.split(",").map(role => ({ roleName: roleNames.trim() }));

      await axios.post("http://localhost:3001/api/auth/register", {
        name,
        userName,
        email,
        password,
        roleNames: rolesArray,
      });

      alert("Registered Successfully");
    } catch (e) {
      console.log("Registration Error", e);
      alert("Registration Failed");
    }
  }

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleRegister}>
        <label htmlFor="name">Name</label>
        <input
          id="name"
          name="name"
          value={name}
          type="text"
          onChange={(e) => setName(e.target.value)}
        />
        <br /><br />

        <label htmlFor="userName">User Name</label>
        <input
          id="userName"
          name="userName"
          value={userName}
          type="text"
          onChange={(e) => setUserName(e.target.value)}
        />
        <br /><br />

        <label htmlFor="email">Email</label>
        <input
          id="email"
          name="email"
          value={email}
          type="email"
          onChange={(e) => setEmail(e.target.value)}
        />
        <br /><br />

        <label htmlFor="password">Password</label>
        <input
          id="password"
          name="password"
          value={password}
          type="password"
          onChange={(e) => setPassword(e.target.value)}
        />
        <br /><br />

        <label htmlFor="roles">Roles (comma-separated)</label>
        <input
          id="roleNames"
          name="roleNames"
          value={roleNames}
          type="text"
          placeholder="e.g., ADMIN,USER"
          onChange={(e) => setRoleNames(e.target.value)}
        />
        <br /><br />

        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;