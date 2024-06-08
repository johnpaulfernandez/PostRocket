const sampleOutput = [
  {
    id: 1,
    title: "Join us at the Kalayaan Cup 2024, the biggest tournament of the Philippine Badminton Club of America! Celebrate Filipino culture through badminton on June 15th, 2024.\n",
  },
  {
    id: 2,
    title: "Don't miss out on the Kalayaan Cup 2024! Limited to 200 participants, so sign up by May 8th, 2024, for a chance to win over 50 raffle prizes.\n",
  },
  {
    id: 3,
    title: "Calling all badminton enthusiasts! Play in events from E to Open level at the Kalayaan Cup 2024. Secure your spot now and receive a tournament shirt.\n",
  },
  {
    id: 4,
    title: "Become a PBCA member for exclusive perks and discounts on tournaments and pro-shop items. Don't miss out on the benefits of membership!\n",
  },
  {
    id: 5,
    title: "Compete in various doubles and mixed events at the Kalayaan Cup 2024. Cash prizes, trophies, and medals await the winners. Sign up now!",
  },
];

document.addEventListener("DOMContentLoaded", () => {
  const generateSampleData = sampleOutput;

  const outputSection = document.getElementById("output-container");

  for (let data of generateSampleData) {
    let card = document.createElement("div");
    card.className = "post";
    card.innerHTML = `<div>${data.title}</div>`;
    outputSection.appendChild(card);
  }
});

