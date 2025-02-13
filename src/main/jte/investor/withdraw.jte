@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param java.util.List<io.caniverse.investment.model.entity.InvestorInvestment> investments
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Withdraw")
</head>
<body
        x-data="{ page: 'withdrawals', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile(investor = investor)
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Make Withdrawal</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">

                    <form action="/investor/withdraw" method="post">
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
                                                @change="isOptionSelected = true" id="investment" name="investorInvestmentId" >
                                            <option value="" class="text-body">
                                                Select Investment
                                            </option>
                                            @for(var investment: investments)
                                            <option value="${investment.getId()}"  data-amount="${investment.getInvestment().getWithdrawalAmount()}">
                                                ${investment.getInvestment().getName()}
                                            </option>
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
                                        Withdrawal amount
                                    </label>
                                    <input type="text" placeholder="Withdrawal amount" id="amount" disabled
                                           class="w-full rounded border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                </div>
                            </div>

                            <div class="mb-4.5 flex flex-col gap-6 xl:flex-row">
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Withdrawal address <span class="text-meta-1">*</span>
                                    </label>
                                    <input type="text" placeholder="Withdrawal address" id="withdrawalAddress" name="address"
                                           class="w-full rounded border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                </div>
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Confirm Withdrawal address <span class="text-meta-1">*</span>
                                    </label>
                                    <input type="text" placeholder="Confirm Withdrawal address" id="confirmWithdrawalAddress"
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

        document.getElementById("amount").value = amount || "";
    }

    function validateForm(){
        let valid = false;
        if( document.getElementById("withdrawalAddress").value != "" &&
        document.getElementById("withdrawalAddress").value.length > 15 &&
        document.getElementById("withdrawalAddress").value == document.getElementById("confirmWithdrawalAddress").value
        ){
            valid = true
        }
        return valid;
    }
    window.onload = function () {
        document.getElementById("investment").addEventListener("change", onInvestmentSelected);
    };
</script>
</body>

</html>