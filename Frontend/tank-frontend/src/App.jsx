import { useEffect, useState } from "react";

function App() {
  const [data, setData] = useState(null);

  // 🔥 Fetch latest data
  const fetchData = async () => {
    try {
      const res = await fetch("http://localhost:8080/latest");
      const result = await res.json();
      setData(result);
    } catch (err) {
      console.error("Error:", err);
    }
  };

  // 🔥 Simulator (sends random data)
  const startSimulator = () => {
    setInterval(async () => {
      const level = Math.floor(Math.random() * 100);

      try {
        const res = await fetch("http://localhost:8080/reading", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ level })
        });

        const data = await res.json();
        console.log("Sent:", level, "Response:", data);
      } catch (err) {
        console.error("Simulator Error:", err);
      }
    }, 5000);
  };

  // 🔁 Run on load
  useEffect(() => {
    fetchData();
    startSimulator(); // ✅ auto start simulator

    const interval = setInterval(fetchData, 5000);
    return () => clearInterval(interval);
  }, []);

  if (!data) return <h2 style={{ textAlign: "center" }}>Loading...</h2>;

  return (
    <div style={styles.container}>
      <h1>💧 Smart Tank Monitoring</h1>

      {/* 🔥 Tank Visualization */}
      <div style={styles.tank}>
        <div
          style={{
            ...styles.water,
            height: `${data.level}%`,
            background:
              data.status === "CRITICAL" ? "red" :
              data.status === "LOW" ? "orange" :
              data.status === "SENSOR_ERROR" ? "purple" :
              "green"
          }}
        ></div>
      </div>

      <h2>Level: {data.level}</h2>

      {/* Status */}
      <div style={styles.card}>
        <h2>Status</h2>
        <p style={{
          ...styles.value,
          color:
            data.status === "CRITICAL" ? "red" :
            data.status === "LOW" ? "orange" :
            data.status === "SENSOR_ERROR" ? "purple" :
            "green"
        }}>
          {data.status}
        </p>
      </div>

      {/* Motor */}
      <div style={styles.card}>
        <h2>Motor</h2>
        <p style={{
          ...styles.value,
          color: data.motorStatus === "ON" ? "green" : "red"
        }}>
          {data.motorStatus}
        </p>
      </div>

      {/* Device */}
      <div style={styles.card}>
        <h2>Device</h2>
        <p style={styles.value}>{data.deviceStatus}</p>
      </div>
    </div>
  );
}

const styles = {
  container: {
    textAlign: "center",
    padding: "20px",
    fontFamily: "Arial"
  },

  tank: {
    height: "200px",
    width: "120px",
    border: "3px solid #333",
    margin: "20px auto",
    position: "relative",
    borderRadius: "10px",
    overflow: "hidden"
  },

  water: {
    position: "absolute",
    bottom: 0,
    width: "100%",
    transition: "height 1s ease"
  },

  card: {
    border: "1px solid #ddd",
    borderRadius: "10px",
    padding: "20px",
    margin: "10px auto",
    width: "300px",
    boxShadow: "0 2px 5px rgba(0,0,0,0.1)"
  },

  value: {
    fontSize: "24px",
    fontWeight: "bold"
  }
};

export default App;