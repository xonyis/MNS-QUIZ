<template>
  <div class="nav-warpper">
    <div id="Navbar">
    <div id="menu">
      <ul>
        <li v-for="icon in icons" :key="icon.id"  class="menu-item">
          <svg :id="icon.id" @click="navigate(icon.route)" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
            <path :d="icon.path" />
          </svg>
          <p class="icon-text">{{ icon.id }}</p>
        </li>
      </ul>
    </div>
  </div>
  </div>
</template>

<script scoped>
export default {
  name: "NavBarComponent",
  data() {
    return {
      icons: [
        {
          id: "Home",
          route: "/home",
          path: "M21 13v10h-6v-6h-6v6h-6v-10h-3l12-12 12 12h-3zm-1-5.907v-5.093h-3v2.093l3 3z",
        },
        {
          id: "Profile",
          route: "/profile",
          path: "M19 7.001c0 3.865-3.134 7-7 7s-7-3.135-7-7c0-3.867 3.134-7.001 7-7.001s7 3.134 7 7.001zm-1.598 7.18c-1.506 1.137-3.374 1.82-5.402 1.82-2.03 0-3.899-.685-5.407-1.822-4.072 1.793-6.593 7.376-6.593 9.821h24c0-2.423-2.6-8.006-6.598-9.819z",
        },
        {
          id: "LeaderBoard",
          route: "/leaderboard",
          path: "M18 10c0 3.309-2.691 6-6 6s-6-2.691-6-6 2.691-6 6-6 6 2.691 6 6zm4 1.737l-1.895 1.168.687 2.095-2.187.46-.079 2.2-2.213-.304-.84 2.04-1.977-1.031-1.496 1.635-1.497-1.634-1.977 1.031-.84-2.04-2.213.304-.079-2.2-2.186-.461.687-2.095-1.895-1.168 1.374-1.737-1.374-1.737 1.895-1.168-.687-2.095 2.187-.46.079-2.2 2.213.304.84-2.04 1.977 1.031 1.496-1.635 1.497 1.634 1.977-1.031.84 2.04 2.213-.304.079 2.2 2.186.461-.687 2.095 1.895 1.168-1.374 1.737 1.374 1.737zm-3-1.737c0-3.866-3.134-7-7-7s-7 3.134-7 7 3.134 7 7 7 7-3.134 7-7zm-1.859 10.276l2.401 3.724 1.146-2h2.312l-2.655-4.103c-.917.969-1.999 1.775-3.204 2.379zm-13.486-2.379l-2.655 4.103h2.312l1.146 2 2.401-3.724c-1.205-.604-2.287-1.41-3.204-2.379z",
        },
        {
          id: "Mentions Légales",
          route: "/metionLegale",
          path: "M10 8h-5v-1h5v1zm0 1h-5v1h5v-1zm0 2h-5v1h5v-1zm-2 2h-3v1h3v-1zm10.692-3.939c-.628-.436-.544-.327-.782-1.034-.099-.295-.384-.496-.705-.496h-.003c-.773.003-.64.044-1.265-.394-.129-.092-.283-.137-.437-.137s-.308.045-.438.137c-.629.442-.492.397-1.265.394h-.003c-.321 0-.606.201-.705.496-.238.71-.156.6-.781 1.034-.198.137-.308.353-.308.578l.037.222c.242.708.242.572 0 1.278l-.037.222c0 .224.11.441.309.578.625.434.545.325.781 1.033.099.296.384.495.705.495h.003c.773-.003.64-.044 1.265.394.129.093.283.139.437.139s.308-.046.438-.138c.625-.438.49-.397 1.265-.394h.003c.321 0 .606-.199.705-.495.238-.708.154-.599.782-1.033.197-.137.307-.355.307-.579l-.037-.222c-.242-.709-.24-.573 0-1.278l.037-.222c0-.225-.11-.443-.308-.578zm-3.192 3.189c-.967 0-1.75-.784-1.75-1.75 0-.967.783-1.751 1.75-1.751s1.75.784 1.75 1.751c0 .966-.783 1.75-1.75 1.75zm1.241 2.758l.021-.008h1.238v7l-2.479-1.499-2.521 1.499v-7h1.231c.415.291.69.5 1.269.5.484 0 .931-.203 1.241-.492zm-16.741-13.008v17h11v-2h-9v-13h20v13h-2v2h4v-17h-24z",
        },
      ],
    };
  },
  methods: {
    navigate(route) {
      this.$router.push(route);
    },
  },
};
</script>

<style>
.nav-warpper {
  position: absolute;
  display: flex;
  align-items: center;
  height: 100vh;
  z-index: 10000;
  /* left: -80px; */
}
#Navbar {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  background-color: #2d1a66;
  color: whitesmoke;
  height: 90vh;
  width: 80px;
  margin-left: 10px;
  border-radius: 20px;
  position: fixed;
  transition: all 200ms ease-in-out;
}


.menu-item {
  display: flex;
  align-items: center;

}

.menu-item p {
  margin-top: 20px;
  display: none;
  margin-left: 10px;
}




ul {
  height: 100%;
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  padding-top: 20px;
  list-style: none;
  padding-left: 25px;
}

svg {
  height: 30px;
  fill: #836bc9;
  cursor: pointer;
  margin-bottom: 30px;
  margin-top: 30px;
}

svg:hover {
  fill: white;
}
#Navbar:hover {
  width: 200px;
  transition: all 200ms ease-in-out;
}

#Navbar:hover .menu-item p {
  display: block;
}
</style>
