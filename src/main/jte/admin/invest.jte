@param java.util.List<io.caniverse.investment.model.entity.Investment> investments
@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Invest")
</head>
<body
        x-data="{ page: 'invest', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.admin.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.admin-profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Make Investment</h2>
                <div class="flex w-full border-l-6 border-warning bg-warning bg-opacity-[15%] px-7 py-8 shadow-md dark:bg-[#1B1B24] dark:bg-opacity-30 md:p-9 mb-3">
                    <div class="mr-5 flex h-9 w-9 items-center justify-center rounded-lg bg-warning bg-opacity-30">
                        <svg width="19" height="16" viewBox="0 0 19 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M1.50493 16H17.5023C18.6204 16 19.3413 14.9018 18.8354 13.9735L10.8367 0.770573C10.2852 -0.256858 8.70677 -0.256858 8.15528 0.770573L0.156617 13.9735C-0.334072 14.8998 0.386764 16 1.50493 16ZM10.7585 12.9298C10.7585 13.6155 10.2223 14.1433 9.45583 14.1433C8.6894 14.1433 8.15311 13.6155 8.15311 12.9298V12.9015C8.15311 12.2159 8.6894 11.688 9.45583 11.688C10.2223 11.688 10.7585 12.2159 10.7585 12.9015V12.9298ZM8.75236 4.01062H10.2548C10.6674 4.01062 10.9127 4.33826 10.8671 4.75288L10.2071 10.1186C10.1615 10.5049 9.88572 10.7455 9.50142 10.7455C9.11929 10.7455 8.84138 10.5028 8.79579 10.1186L8.13574 4.75288C8.09449 4.33826 8.33984 4.01062 8.75236 4.01062Z"
                                  fill="#FBBF24"></path>
                        </svg>
                    </div>
                    <div class="w-full">
                        <h5 class="mb-3 text-lg font-bold text-[#9D5425]">
                            Attention needed
                        </h5>
                        <p class="leading-relaxed text-[#D0915C]">
                            Place an investment for ${investor.getName()}
                        </p>
                    </div>
                </div>

                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <form method="post" action="/admin/investors/${investor.getId()}/invest" onsubmit="return validateForm()">
                        <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
                        <div class="p-6.5">

                            <div class="mb-4.5 flex flex-col gap-6 xl:flex-row">
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Select Investment
                                    </label>
                                    <div x-data="{ isOptionSelected: false }"
                                         class="relative z-20 bg-transparent dark:bg-form-input">
                                        <select class="relative z-20 w-full appearance-none rounded border border-stroke bg-transparent px-5 py-3 outline-none transition focus:border-primary active:border-primary dark:border-form-strokedark dark:bg-form-input dark:focus:border-primary"
                                                :class="isOptionSelected &amp;&amp; 'text-black dark:text-white'"
                                                @change="isOptionSelected = true" name="investmentId" id="investment" required>
                                            <option value="" class="text-body">
                                                Select Investment
                                            </option>
                                            @for(var investment: investments)
                                                <option value="${investment.getId()}" class="text-body">${investment.getName()}</option>
                                            @endfor
                                        </select>
                                        <span class="absolute right-4 top-1/2 z-30 -translate-y-1/2">
                                        <svg class="fill-current" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                             xmlns="http://www.w3.org/2000/svg">
                                          <g opacity="0.8">
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                  d="M5.29289 8.29289C5.68342 7.90237 6.31658 7.90237 6.70711 8.29289L12 13.5858L17.2929 8.29289C17.6834 7.90237 18.3166 7.90237 18.7071 8.29289C19.0976 8.68342 19.0976 9.31658 18.7071 9.70711L12.7071 15.7071C12.3166 16.0976 11.6834 16.0976 11.2929 15.7071L5.29289 9.70711C4.90237 9.31658 4.90237 8.68342 5.29289 8.29289Z"
                                                  fill=""></path>
                                          </g>
                                        </svg>
                                    </span>
                                    </div>
                                </div>

                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Investment amount
                                    </label>
                                    <input type="text" name="amount"  id="investmentAmount" placeholder="Investment amount"  required
                                           class="w-full rounded border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                </div>
                            </div>

                            <div class="mb-4.5 flex flex-col gap-6 xl:flex-row">
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Deposit Tx Id <span class="text-meta-1">*</span>
                                    </label>
                                    <input type="text" id="transactionHash" name="transactionHash"
                                           placeholder="Deposit Tx Id"
                                           required
                                           class="w-full rounded border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                </div>
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Confirm Deposit Tx Id <span class="text-meta-1">*</span>
                                    </label>
                                    <input type="text" id="confirmTransactionHash" placeholder=" Confirm Deposit Tx Id"
                                           required
                                           class="w-full rounded border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                </div>
                            </div>

                            <button class="flex w-full justify-center rounded bg-primary p-3 font-medium text-gray hover:bg-opacity-90">
                                Submit Request
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
<script>
    function onInvestmentSelected() {
        const selectElement = document.getElementById("investment");

        const selectedOption = selectElement.options[selectElement.selectedIndex];
        const amount = selectedOption.getAttribute("data-amount");

        document.getElementById("investmentAmount").value = amount || "";
    }

    function validateForm(){
        let valid = false;
        if( document.getElementById("transactionHash").value != "" &&
        document.getElementById("transactionHash").value.length > 4 &&
        document.getElementById("transactionHash").value == document.getElementById("confirmTransactionHash").value
        ){
            valid = true
        }
        if(!valid){
            alert("Please fill all required fields")
        }
        return valid;
    }
    window.onload = function () {
        document.getElementById("investment").addEventListener("change", onInvestmentSelected);
    };
</script>
</body>

</html>