module.exports = {
  client: {
    service: "com.isep.dailyartapp",
    headers: {
        "Content-Type": "application/json",
        "auth-token": "cb3df41b-aefc-4e3a-9c39-fa315babc975"
    },
    includes: ['./src/main/graphql/**/*.js'],
    excludes: ['./node_modules']
  },
};